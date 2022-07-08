{-# LANGUAGE TupleSections #-}
--For part A I decided to make the knights Tour. I had to change some stuff around with the search algorithm and some other things in order to impliment this correctly.
--The way for you to test this function is by running the method called Main. This will run the methods I created to do the knights tour. And it will do the tour from the starting value of e5
--The starting point is always the same, due to the knights tour I have seen worked based on the middle point of the chess board.
module MP5a where

import Data.Maybe
import Data.Char (ord, chr)
import Data.Bool (bool)
import Data.Ord
import Data.List
import Data.List
import Data.Tree
import Data.Map (Map, empty, fromList, (!), keys, elems, assocs,
                 findWithDefault, member, insert, insertWith)
import System.Random
import System.Random.Shuffle
import Control.Concurrent
import Control.Monad.State
import System.IO
import System.Console.ANSI
import GHC.IO


-- Search function from lecture notes
search :: (Eq a, Show a) =>
          (a -> Bool)
          -> (a -> [a])
          -> ([a] -> [a] -> [a])
          -> [a] -> [a]
          -> Maybe a
search goal adj comb unvisited visited
  | null unvisited = Nothing
  | goal (head unvisited) = Just (head unvisited)
  | otherwise = let (n:ns) = unvisited
                in -- debug n $ -- uncomment to "debug"
                   search goal adj comb
                          (comb (removeDups (adj n)) ns)
                          (n:visited)
  where removeDups = filter (not . (`elem` (unvisited ++ visited)))


debug :: Show a => a -> b -> b
debug x y = unsafePerformIO clearScreen `seq`
            unsafePerformIO (setCursorPosition 0 0) `seq`
            unsafePerformIO (putStrLn $ show x) `seq`
            unsafePerformIO (threadDelay $ 3*10^5) `seq`
            y


-- Call with an admissible heuristic as the cost function to carry out A* search
bestFirstSearch :: (Eq a, Show a, Ord b) =>
                   (a -> Bool)
                   -> (a -> [a])
                   -> (a -> b)
                   -> a -> Maybe a
bestFirstSearch goal succ cost start = search goal succ comb [start] []
  where comb new old = sortOn cost (new ++ old)

type Square = (Int, Int)
 
knightTour :: [Square] -> [Square]
knightTour moves
  | null possibilities = reverse moves
  | otherwise = knightTour $ newSquare : moves
  where
    newSquare = minimumBy (comparing (length . findMoves)) possibilities
    possibilities = findMoves $ head moves
    findMoves = (\\ moves) . knightOptions
 
knightOptions :: Square -> [Square]
knightOptions (x, y) =
  knightMoves >>=
  (\(i, j) ->
      let a = x + i
          b = y + j
      in bool [] [(a, b)] (onBoard a && onBoard b))
 
knightMoves :: [(Int, Int)]
knightMoves =
  let deltas = [id, negate] <*> [1, 2]
  in deltas >>=
     (\i -> deltas >>= (bool [] . return . (i, )) <*> ((abs i /=) . abs))
 
onBoard :: Int -> Bool
onBoard = (&&) . (0 <) <*> (9 >)

startPoint = "e5"

algebraic :: (Int, Int) -> String
algebraic (x, y) = [chr (x + 96), chr (y + 48)]

main :: IO ()
main =
  printTour $
  algebraic <$> knightTour [(\[x, y] -> (ord x - 96, ord y - 48)) startPoint]
  where
    printTour [] = return ()
    printTour tour = do
      putStrLn $ intercalate " -> " $ take 8 tour
      printTour $ drop 8 tour