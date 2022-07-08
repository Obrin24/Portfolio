package Project;

public abstract class Stations {
	//Class Variables
	private String name;
	private double longitude;
	private double latitude;
	private String description;
	private Boolean wheelchair;
	private int[] positions;
	
	//Default constructor
	public Stations() {
		name = "";
		longitude = 0.0;
		latitude = 0.0;
		description = "";
		wheelchair = true;
		positions = new int[7];
	}
	//Nondefault Constructor
	public Stations(String n, double lo, double la, String d, boolean w, int s[]) {
		setName(n);
		setLongitude(lo);
		setLatitude(la);
		setDescription(d);
		setWheelchair(w);
		setPositions(s);
	}
	//Mutators
	public void setName(String name) {
		this.name = name;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setWheelchair(boolean wheelchair) {
		this.wheelchair = wheelchair;
	}
	public void setPositions(int[]positions ) {
		this.positions = new int[positions.length];
		for(int i = 0; i<positions.length;i++) {
			this.positions[i] = positions[i];
		}
	}
	//Acessor Methods
	public String getName() {
		return this.name;
	}
	public double getLongitude() {
		return this.longitude;
	}
	public double getLatitude() {
		return this.latitude;
	}
	public String getDescription() {
		return this.description;
	}
	public boolean getWheelchair() {
		return this.wheelchair;
	}
	public int[] getPositions() {
		return positions;
	}
	//ToString Method
	public String toString() {
		return "Name: " + name + " Longitude: " + longitude + " Latitude: " + latitude + " Description: " + description + " Wheelchair Access: " + wheelchair + " Positions: " + printPositions() + ".";
	}
	//Equals method
	public boolean equals(Stations other) {
		if(this.name.equals(other.getName())) {
			if(this.longitude==other.getLongitude()) {
				if(this.latitude==other.getLatitude()) {
					if(this.description.equals(other.getDescription())) {
						if(this.wheelchair==other.getWheelchair()) {
							if(this.getPositions().equals(other.getPositions())) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	public String printPositions() {
		String p = "";
		for(int i = 0; i<positions.length;i++) {
			String x = "";
			if(i==0) {
				x = "{" + positions[i] + ", ";
			}else if(i==positions.length-1) {
					x = positions[positions.length-1] + "}";
			}else {
				x = positions[i] + ", ";
			}
			p+=x;
		}
		return p;
	}
	//Pathing between Stations Method
	public abstract void StationPathing(String StationOne, String StationTwo);
}
