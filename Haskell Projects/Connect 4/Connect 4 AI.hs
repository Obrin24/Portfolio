--For this machine problem I created a connect 4 AI. This was done with the minimax function, but I altered it since I found it easier to do without a search tree
--Instead I created several methods that scanned the rows, and cols to see the best placement. Along with that the board changes every time and the AI runs through the altered minimax code I created to find the optimal place.
--Now the AI is a little dumb sometimes but it can win. To test this, and to play the game you can run the function playAI. This will run through the game of connect 4.
module MP5b where

import Data.Maybe
import Data.Ord
import Data.List
import Data.Tree ()
import Data.Map (Map, empty, fromList, (!), findWithDefault, member, insert, insertWith,lookup)
import System.Random
import System.Random.Shuffle
import Control.Concurrent
import Control.Monad.State
import System.IO
import System.Console.ANSI
import GHC.IO
import Data.Char


{- Replace with your own game data types  -}

data Piece = X | O
 deriving (Show, Eq)

data Board = B { piecesMap :: Map (Int, Int) Piece,
                 heightMap :: Map Int Int }

instance Show Board where
  show = boardString

boardString :: Board -> String
boardString = show' 6
  where show' :: Int -> Board -> String
        show' 0 _ = ""
        show' n b = show (map getPlayer (row n 1 b)) ++ "\n" ++ 
                    (show' (n-1) b)
          where getPlayer :: Maybe Piece -> String
                getPlayer (Just p) = show p
                getPlayer Nothing = " "

move :: Board -> Piece -> Int -> Maybe Board
move (B pieces height) player c
  | c < 1 || c > 7              = Nothing
  | Data.Map.lookup c height == Just 6 = Nothing
  | otherwise                   = 
      case (Data.Map.lookup c height) of
        Nothing -> 
          Just $ B (Data.Map.insert (1, c) player pieces) (Data.Map.insert c 1 height)
        Just y  -> 
          Just $ B (Data.Map.insert (y+1, c) player pieces) (Data.Map.insert c (y+1) height)

checkWinner :: Piece -> Board -> Bool
checkWinner p board = checkLists p (rows 1 board) || 
                         checkLists p (cols 1 board) ||
                         checkLists p (diags 1 board)

checkLists :: Piece -> [[Maybe Piece]] -> Bool
checkLists _ []     = False
checkLists p (x:xs) = checkList 0 p x || checkLists p xs

checkList :: Int -> Piece -> [Maybe Piece] -> Bool
checkList _ _ []            = False
checkList 4 _ _             = True
checkList n X ((Just X):xs) = checkList (n+1) X xs
checkList n O ((Just O):xs) = checkList (n+1) O xs
checkList _ p (_:xs)        = checkList 0 p xs

rows :: Int -> Board -> [[Maybe Piece]]
rows 7 _ = []
rows n b = row n 1 b : (rows (n+1) b)

row :: Int -> Int -> Board -> [Maybe Piece]
row _ 8 _ = []
row n x b = Data.Map.lookup (n, x) (piecesMap b) : (row n (x+1) b)

cols :: Int -> Board -> [[Maybe Piece]]
cols 8 _ = []
cols n b = col n 1 b : (cols (n+1) b)

col :: Int -> Int -> Board -> [Maybe Piece]
col _ 7 _ = []
col n x b = Data.Map.lookup (x, n) (piecesMap b) : (col n (x+1) b)

diags :: Int -> Board -> [[Maybe Piece]]
diags 7 _ = []
diags n b  = diagUR n (-1) b : ((diagUL n 9 b) : (diags (n+1) b))

diagUR :: Int -> Int -> Board -> [Maybe Piece]
diagUR _ 7 _ = []
diagUR n x b = Data.Map.lookup (n, x) (piecesMap b) : (diagUR (n+1) (x+1) b)

diagUL :: Int -> Int -> Board -> [Maybe Piece]
diagUL _ 7 _ = []
diagUL n x b = Data.Map.lookup (n, x) (piecesMap b) : (diagUL (n+1) (x-1) b)

full :: Board -> Bool
full b = 6 == length (takeWhile (==7) 
         $ map (length . (takeWhile fun)) (rows 1 b))
  where fun :: Maybe Piece -> Bool
        fun Nothing  = False
        fun (Just _) = True

emptyBoard :: Board
emptyBoard = B (fromList []) (fromList [])

genBoard :: Board -> Piece -> [(Board, Int)]
genBoard b p = prune $ zip (map (move b p) [1..7]) [1..7]

prune :: [(Maybe Board, Int)] -> [(Board, Int)]
prune [] = []
prune ((Nothing, _):xs)  = prune xs
prune (((Just b), n):xs) = (b, n) : (prune xs)

getMove :: Board -> Int
getMove b = snd $ maximum $ map (minimax 3 X) (genBoard b O)

playersTurn :: Board -> IO ()
playersTurn b = do
  putStrLn ""
  putStrLn "User's move. Type any column number from 1 to 7."
  attempt <- getLine
  case attempt of
    []     -> putStrLn "Invalid input." >> playersTurn b
    (x:[]) -> if isDigit x
                then case (move b X (read attempt)) of
                       Nothing -> putStrLn "Move failed." >> playersTurn b
                       Just b' -> putStrLn "Move successful." >>
                         putStr (show b') >> playGame b' False
    else putStrLn "Invalid input." >> playersTurn b
    _      -> putStrLn "Invalid input." >> playersTurn b

computersTurn :: Board -> IO ()
computersTurn b = do
  putStrLn ""
  attempt <- return (getMove b)
  case (move b O attempt) of
    Nothing -> putStrLn "Computer's move failed." >> computersTurn b
    Just b' -> putStrLn "Computer's move successful." >>
               putStr (show b') >> playGame b' True

opp :: Piece -> Piece
opp X = O
opp O = X

value :: Board -> Int
value b
  | checkWinner O b = 100000
  | checkWinner X b = -100000
  | full b          = 0
  | otherwise       = score b

score :: Board -> Int
score b = 100*((waysToWin list O) - (waysToWin list X))
  where list = cols 1 b ++ (rows 1 b) ++ (diags 1 b)  

waysToWin :: [[Maybe Piece]] -> Piece -> Int
waysToWin [] _ = 0
waysToWin (x:xs) p = countThrees x p + (waysToWin xs p)


countThrees :: [Maybe Piece] -> Piece -> Int
countThrees [] _ = 0
countThrees (x:xs) p 
  | countP p (take 4 (x:xs)) 0 = 1 + (countThrees xs p)
  | otherwise                  = countThrees xs p

countP :: Piece -> [Maybe Piece] -> Int -> Bool
countP _ [] 3 = True
countP _ [] _ = False
countP p (Nothing:xs) counter = countP p xs counter
countP p ((Just x):xs) counter
  | p == x    = countP p xs (counter+1)
  | otherwise = False
  
-- Minimax function from lecture notes
minimax :: Int  -> Piece -> (Board, Int) -> (Int, Int)
minimax 0 p (b, m) = case p of
                      O -> (value b, m)
                      X -> (-(value b), m)
minimax  _ p (b, m)
  | full b || checkWinner X b || checkWinner O b = case p of
                                                     O -> (value b, m)
                                                     X -> (-(value b), m)
minimax d p (board, m) =
  ((maximum $ map ((*(-1)) . fst) $ map (minimax (d-1) (opp p))
  (genBoard board (opp p))), m)

-- Plays a game with the AI
playAI :: IO ()
playAI = do 
  putStrLn "User: X"
  putStrLn "Computer: 0"
  putStr (show emptyBoard)
  playGame emptyBoard True

playGame :: Board -> Bool -> IO ()
playGame b user
  | checkWinner X b = putStrLn "You win!"
  | checkWinner O b = putStrLn "The Computer wins!"
  | full b          = putStrLn "The board is full. The game ends in a tie."
  | user            = playersTurn b
  | otherwise       = computersTurn b