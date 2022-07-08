package Project;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	public static Scanner scan = new Scanner(System.in);
	static ArrayList<Stations> AllStations = new ArrayList<Stations>();
	public static void main(String[] args) {
		//Initial Stages
		Line a = new Line();
		a.AllStations();
		System.out.println("\r\n" + 
				"________/\\\\\\\\\\\\\\\\\\__/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_____/\\\\\\\\\\\\\\\\\\__________________/\\\\\\\\\\\\\\\\\\\\\\____/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_______/\\\\\\\\\\_______/\\\\\\\\\\\\\\\\\\\\\\\\\\_______/\\\\\\\\\\\\\\\\\\\\\\___        \r\n" + 
				" _____/\\\\\\////////__\\///////\\\\\\/////____/\\\\\\\\\\\\\\\\\\\\\\\\\\______________/\\\\\\/////////\\\\\\_\\///////\\\\\\/////______/\\\\\\///\\\\\\____\\/\\\\\\/////////\\\\\\___/\\\\\\/////////\\\\\\_       \r\n" + 
				"  ___/\\\\\\/_________________\\/\\\\\\________/\\\\\\/////////\\\\\\____________\\//\\\\\\______\\///________\\/\\\\\\_________/\\\\\\/__\\///\\\\\\__\\/\\\\\\_______\\/\\\\\\__\\//\\\\\\______\\///__      \r\n" + 
				"   __/\\\\\\___________________\\/\\\\\\_______\\/\\\\\\_______\\/\\\\\\_____________\\////\\\\\\_______________\\/\\\\\\________/\\\\\\______\\//\\\\\\_\\/\\\\\\\\\\\\\\\\\\\\\\\\\\/____\\////\\\\\\_________     \r\n" + 
				"    _\\/\\\\\\___________________\\/\\\\\\_______\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\________________\\////\\\\\\____________\\/\\\\\\_______\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\/////////_________\\////\\\\\\______    \r\n" + 
				"     _\\//\\\\\\__________________\\/\\\\\\_______\\/\\\\\\/////////\\\\\\___________________\\////\\\\\\_________\\/\\\\\\_______\\//\\\\\\______/\\\\\\__\\/\\\\\\_____________________\\////\\\\\\___   \r\n" + 
				"      __\\///\\\\\\________________\\/\\\\\\_______\\/\\\\\\_______\\/\\\\\\____________/\\\\\\______\\//\\\\\\________\\/\\\\\\________\\///\\\\\\__/\\\\\\____\\/\\\\\\______________/\\\\\\______\\//\\\\\\__  \r\n" + 
				"       ____\\////\\\\\\\\\\\\\\\\\\_______\\/\\\\\\_______\\/\\\\\\_______\\/\\\\\\___________\\///\\\\\\\\\\\\\\\\\\\\\\/_________\\/\\\\\\__________\\///\\\\\\\\\\/_____\\/\\\\\\_____________\\///\\\\\\\\\\\\\\\\\\\\\\/___ \r\n" + 
				"        _______\\/////////________\\///________\\///________\\///______________\\///////////___________\\///_____________\\/////_______\\///________________\\///////////_____\r\n" + 
				"\n");
		AllStations = a.getTotalStations();
		Line Red = new Line("Red",AllStations);
		Line Green = new Line("Green",AllStations);
		Line Blue = new Line("Blue",AllStations);
		Line Brown = new Line("Brown",AllStations);
		Line Purple = new Line("Pruple",AllStations);
		Line Pink = new Line("Pink",AllStations);
		Line Orange = new Line("Orange",AllStations);
		BinarySearch x1 = new BinarySearch();
		ArrayList<Line> AllLines = new ArrayList<Line>();
		//Menu
		boolean done = false;
		do {
			System.out.println("Welcome to the CTA Menu!");
			System.out.println("[1.]\tCreate a new Station");
			System.out.println("[2.]\tModify a Station");
			System.out.println("[3.]\tRemove a new Station");
			System.out.println("[4.]\tSearch for a CTA Station");
			System.out.println("[5.]\tFind a CTA Station with Wheelchair access");
			System.out.println("[6.]\tFind the Nearest CTA Station");
			System.out.println("[7.]\tGenerate the Path between two Stations");
			System.out.println("\n\n[8.]\tExit");
			int input = Integer.parseInt(scan.nextLine());
			if(input==1) {
				Stations A = a.addStation2();
				AllStations.add(A);
				for(int i = 0;i<AllLines.size();i++) {
					AllLines.get(i).addStation(A);
				}
				System.out.println("Station Created and added to lines");
			}else if(input == 2) {
				System.out.println("Select the Station you want to modify");
				a.PrintStations2();
				int A = Integer.parseInt(scan.nextLine());
				Stations B = Red.modifyStation(A-1);
				AllStations.set(A-1,B);
				for(int i = 0;i<AllLines.size();i++) {
					AllLines.get(i).replaceStation(A-1, B);
				}
			}else if(input == 3) {
				a.PrintStations2();
				System.out.println("Select the Station you want to remove");
				int A = Integer.parseInt(scan.nextLine());
				AllStations.remove(A-1);
				for(int i = 0;i<AllLines.size();i++) {
					AllLines.get(i).removeStation(A);
				}
			}else if(input == 4) {
				System.out.println("Enter the Name of the Station you want to find");
				String name = scan.nextLine();
				String s = x1.searchName(AllStations, name);
				System.out.println(s);
			}else if(input == 5) {
				System.out.println("Enter the Wheelchair access status of the Station you want to find");
				boolean wheelchair = Boolean.parseBoolean(scan.nextLine());
				String s = x1.searchWheelchair(AllStations, wheelchair);
				System.out.println(s);
			}else if(input == 6) {
				System.out.println("Enter your Latitude");
				double Latitude = Double.parseDouble(scan.nextLine());
				System.out.println("Enter your Longitude");
				double Longitude = Double.parseDouble(scan.nextLine());
				System.out.println("The Station that is nearest to your location is: " + x1.searchStation(AllStations, Latitude, Longitude));
			}else if(input == 7) {
				System.out.println("Enter the name of the first Station");
				String one = scan.nextLine();
				System.out.println("Eneter the name of the second Station");
				String two = scan.nextLine();
				Red.StationPathing(one, two);
			}else if(input == 8) {
				System.out.println("Exiting the CTA System Program. Thank you for using!");
				done = true;
			}
		}while(!done);
	}
}

