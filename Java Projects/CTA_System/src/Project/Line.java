package Project;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class Line extends Stations {
	//Private variables
	private String LineName;
	private ArrayList<Stations> Stations = new ArrayList<Stations>();
	public static ArrayList<Stations> AllStations = new ArrayList<Stations>();
	private int LineNumber;
	private String[] Lines = {"Red", "Green", "Blue", "Brown", "Purple", "Pink", "Orange"};
	//Default constructor
	public Line() {
		// TODO Auto-generated constructor stub
		LineName = "";
		LineNumber = 0;
	}
	//Nondefault constructors
	public Line(String n, double lo, double la, String d, boolean w, int s[]) {
		super(n,lo,la,d,w,s);
	}
	public Line(String ln, ArrayList<Stations>Stations) {
		SetLineName(ln);
		setLineNumber();
		SetStations(Stations);
		AllStations = Stations;
		// TODO Auto-generated constructor stub
		
	}
	//Mutator Methods
	public void SetLineName(String LineName) {
		this.LineName = LineName;
	}
	public void SetStations(ArrayList<Stations> Stations) {
		for(int k = 0;k<Stations.size();k++) {
			int[] x = Stations.get(k).getPositions();
			if(x[LineNumber]!=-1) {
				this.Stations.add(Stations.get(k));
			}
		}
		sortStations();
	}
	public void setLineNumber() {
		for(int j = 0; j<Lines.length;j++) {
			if(Lines[j].equals(LineName)) {
				LineNumber = j;
			}
		}
	}
	//Accessor Methods
	public String getLineName() {
		return LineName;
	}
	public ArrayList<Stations> getStations() {
		return Stations;
	}
	public int getLineNumber() {
		return LineNumber;
	}
	public ArrayList<Stations> getTotalStations(){
		return AllStations;
	}
	//ToString Method
	public String ToString() {
		return "Line Name: " + LineName + " Stations: " + PrintStations() + ".";
	}
	//Equals Method
	public boolean Equals(Line other) {
		if(this.LineName.equals(other.getLineName())) {
			if(this.getStations().equals(other.getStations())) {
				if(this.LineNumber==other.getLineNumber()) {
					return true;
				}
			}
		}
		return false;
	}
	//AddStation Method Part 1
	public void addStation(Stations A) {
		this.Stations.add(A);
		SetStations(Stations);
		AllStations.add(A);
		sortStations();
	}
	//RemoveStation Method
	public void removeStation(int A) {
		this.Stations.remove(A-1);
		SetStations(Stations);
		AllStations.remove(A-1);
	}
	//Modify Station
	public Stations modifyStation(int A) {
		System.out.print(AllStations.get(A));
		Stations B = AllStations.get(A);
		Scanner scan = new Scanner(System.in);
		System.out.println("What do you want to modify?(Name, Longatude, Latitude, Description, Wheelchair Access)");
		String input = scan.nextLine();
		if(input.equalsIgnoreCase("Name")) {
			System.out.println("What is the new name?");
			String x = scan.nextLine();
			B.setName(x);
		}else if(input.equalsIgnoreCase("Longatude")) {
			System.out.println("What is the new Longitude?");
			double a = Double.parseDouble(scan.nextLine());
			B.setLongitude(a);
		}else if(input.equalsIgnoreCase("Latitude")) {
			System.out.println("What is the new Latitude?");
			double b = Double.parseDouble(scan.nextLine());
			B.setLongitude(b);
		}else if(input.equalsIgnoreCase("Description")) {
			System.out.println("What is the new Description?");
			String y = scan.nextLine();
			B.setDescription(y);
		}else if(input.equalsIgnoreCase("Wheelchair Access")) {
			System.out.println("What is the new Wheelchair Access status?");
			Boolean w = Boolean.parseBoolean(scan.nextLine());
			B.setWheelchair(w);
		}
		return B;
		
	}
	//Replace Station
	public void replaceStation(int a, Stations b) {
		Stations.set(a,b);
	}
	//Print Stations Method
	public String PrintStations() {
		String x = "";
		for(int i = 0; i<Stations.size();i++) {
			if(i==0) {
				x+= "{" + Stations.get(i).getName() + ", ";
			}else {
				if(i==Stations.size()) {
					x+= (Stations.get(i).getName() + "}");
				}
				x+= (Stations.get(i).getName() + ", ");
			}
		}
		return x;
	}
	//Sort Stations
	public void sortStations() {
		boolean done = true;
		do {
			done = true;
			for(int i=1; i<Stations.size(); i++) {
				int[] x = Stations.get(i).getPositions();
				int[] y = Stations.get(i-1).getPositions();
				if(y[LineNumber] > x[LineNumber]) {
					//Swaping for the bubble algorithm
					Stations temp = Stations.get(i-1);
					Stations.set(i-1, Stations.get(i));
					Stations.set(i,temp);
					done = false;
				}
			}
		}while (!done);
	}
	
	//Method for finding the path between two different stations
	public void StationPathing(String StationOne, String StationTwo) {
		// TODO Auto-generated method stub
		int count = 0;
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> stationsOne = new ArrayList<Integer>();
		ArrayList<Integer>  stationsTwo = new ArrayList<Integer>();
		int count2 = 0;
		for(int i = 0;i<AllStations.size();i++) {
			if(AllStations.get(i).getName().equals(StationOne)) {
				count++;
				stationsOne.add(i);
			}else if(AllStations.get(i).getName().equals(StationTwo)) {
				count2++;
				stationsTwo.add(i);
			}
		}
		if(count>1) {
			System.out.println("Which station are you refering too for station one?");
			for(int j = 0;j<stationsOne.size();j++) {
				System.out.println(AllStations.get(stationsOne.get(j)));
			}
			int x = Integer.parseInt(scan.nextLine());
			Stations A = AllStations.get(x);
			if(count2>1) {
				System.out.println("Which station are you refering too for station one?");
				for(int j = 0;j<stationsTwo.size();j++) {
					System.out.println(AllStations.get(stationsTwo.get(j)));
				}
				int b = Integer.parseInt(scan.nextLine());
				Stations B = AllStations.get(b);
			}
		}else if(count2>1) {
			System.out.println("Which station are you refering too for station one?");
			for(int j = 0;j<stationsTwo.size();j++) {
				System.out.println(AllStations.get(stationsTwo.get(1)));
			}
			int l = Integer.parseInt(scan.nextLine());
			Stations B = AllStations.get(l);
		}
		Stations A = AllStations.get(stationsOne.get(0));
		Stations B = AllStations.get(stationsTwo.get(0));
		System.out.println("What line is station one on?");
		String one = scan.nextLine();
		System.out.println("What line is station two one?");
		String two = scan.nextLine();
		int lineNumber1 = findLineNumber(one);
		int lineNumber2 = findLineNumber(two);
		int First = 0;
		int Second = 0;
		if(one.equals(two)) {
			System.out.println("It is a stright shot from Station one to Station two on the line" + one);
		}else {
			for(int j = 0;j<AllStations.size();j++) {
				if(AllStations.get(j).equals(A)) {
					First=j;
				}
			}
			for(int j = 0;j<AllStations.size();j++) {
				if(AllStations.get(j).equals(B)) {
					Second=j;
				}
			}
			if(First>Second) {
				for(int i = First;i<AllStations.size();i--) {
					if(i<0) {
						break;
					}else {
					int[] positions = AllStations.get(i).getPositions(); 
					if(positions[lineNumber2]>-1&&positions[lineNumber1]>-1) {
						System.out.println("Transfer from the " + one + " to the " + two + " at the Station " + AllStations.get(i).getName() + " to get to the Final Station");
					}
					}
				}
			}else if(First<Second) {
				System.out.println("Transfer at any of these transfer points to get to your destination!");
				for(int i = First;i<AllStations.size();i++) {
					int[] positions = AllStations.get(i).getPositions(); 
					if(positions[lineNumber2]>-1&&positions[lineNumber1]>-1) {
						System.out.println("Transfer from the " + one + " to the " + two + " at the Station " + AllStations.get(i).getName() + " to get to the Final Station");
					}
				}
			}
			
		}	
	}
	//AllStations Method
	public void AllStations() {
		try {
	    	File myObj = new File("src/project/CTAStops.csv");
			Scanner scan = new Scanner(myObj);
			while(scan.hasNextLine()) {
				String data = scan.nextLine();
				if(data.contains("Latitude")) {
					System.out.println();
				}else {
				String n = data.substring(0,data.indexOf(','));
				data = data.substring(data.indexOf(',')+1);
				double la = Double.parseDouble(data.substring(0,data.indexOf(',')));
				data = data.substring(data.indexOf(',')+1);
				double ln = Double.parseDouble(data.substring(0,data.indexOf(',')));
				data = data.substring(data.indexOf(',')+1);
				String d = data.substring(0,data.indexOf(','));
				data = data.substring(data.indexOf(',')+1);
				Boolean w = Boolean.parseBoolean(data.substring(0,data.indexOf(',')));
				data = data.substring(data.indexOf(',')+1);
				int [] x = new int[8];
				for(int i = 0;i<x.length;i++) {
					if(!data.contains(",")) {
						x[i]=Integer.parseInt(data);
					}else {
					x[i]=Integer.parseInt(data.substring(0,data.indexOf(',')));
					data = data.substring(data.indexOf(',')+1);
				}
				}
				AllStations.add(new Line(n,la,ln,d,w,x));
			}
			}
		}catch (FileNotFoundException myObj) {
			// TODO Auto-generated catch block
			System.out.println("An error occurred.");
		}
}
	//AddStation Menu Side
	public Stations addStation2() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Creating a new station");
		System.out.println("Please enter the Station name");
		String name = scan.nextLine();
		System.out.println("Please enter the Station Longatude");
		double ln = Double.parseDouble(scan.nextLine());
		System.out.println("Please enter the Station Latitude");
		double la = Double.parseDouble(scan.nextLine());
		System.out.println("Please enter the Station Description");
		String d = scan.nextLine();
		System.out.println("Please enter the Stations Wheelchair Access(True/False)");
		Boolean w = Boolean.parseBoolean(scan.nextLine());
		System.out.println("Now we are going to mark the positions on the different lines");
		int [] x = new int[8];
		for(int i = 0;i<x.length;i++) {
			System.out.println("Enter the next Position");
			x[i]=Integer.parseInt(scan.nextLine());
		}
		Line A = new Line(name,la,ln,d,w,x);
		return A;
		
	}
	//Print All Stations Method
	public String PrintStations2() {
		String x = "";
		for(int i = 0; i<AllStations.size();i++) {
			System.out.println(i+1 + ". " + AllStations.get(i).getName());
		}
		return x;
	}
	//Find LineNumber
	public int findLineNumber(String name) {
		int lineNumber = 0;
		for(int j = 0; j<Lines.length;j++) {
			if(Lines[j].equals(name)) {
				lineNumber = j;
			}
		}
		return lineNumber;
	}
}

