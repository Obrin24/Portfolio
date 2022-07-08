package Project;

import java.util.*;

	public class BinarySearch {
		//Searching for the Stations with the name the user provided.
	public String searchName(ArrayList<Stations>a,String name) {
		String s = "";
		for(int i = 0; i<a.size();i++) {
			if(a.get(i).getName().equals(name)) {
				s+= a.get(i) + "\n";
			}
		}
		return s;
	}
	//Seaching for the Stations with the desciption they provided.
	public String searchWheelchair(ArrayList<Stations>a,boolean wheelchair) {
		String s = "";
		for(int i = 0; i<a.size();i++) {
			if(a.get(i).getWheelchair()==wheelchair) {
				s+= a.get(i) + "\n";
			}
		}
		return s;
	}
	//Seaching for a Station close to your location
	public Stations searchStation(ArrayList<Stations>a,double Latitude, double Longitude) {
		int count = 0;
		double previousDistance = 9999999.9;
		double currentDistance = 0.0;
		for(int i = 0; i<a.size();i++) {
			currentDistance = Math.sqrt(Math.exp(a.get(i).getLatitude()-Latitude) + Math.exp(a.get(i).getLongitude()-Longitude));
			if(currentDistance<previousDistance) {
				count = i;
				previousDistance = currentDistance;
			}
		}
		return a.get(count);
	}
}
