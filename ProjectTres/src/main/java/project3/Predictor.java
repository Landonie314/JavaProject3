/*
 * Landon Jones
 * Project 3
 * 04/11/2023
 */
package project3;
import java.io.*;
import java.util.*;

//What is the point of the predictor class??
public class Predictor {
	private String fileName;
	private ArrayList <Instance> pre;
	private Random rng;
	//All members needed for a randomized instance
	private Instance yep;
	private int big, small, coin;
	private String [] weather;
	private String [] activity;
	private String thing;
	private ArrayList <String> acty;
	private ArrayList <String> wety;
		
	
		
		//Constructor
		public Predictor(String fn) {
			pre = new ArrayList<Instance>();
			fileName = fn;
			readFile();
		}
		
		//adds instance to the ArrayList of instances
		public void addInstance(Instance i) {
			pre.add(i);
		}
		
		//Gets an instance
		public Instance getInstance (int i) {
			return pre.get(i);
		}
		
		//Gets the size of the arraylist
		public int getSize() {
			return pre.size();
		}
		//Removes an instance
		public void removeInstance(int i) {
			pre.remove(i);
		}
		
		//Changes the activity based on the day's attributes
		public void changeActivity() {
			
			//cycles the ArrayList pre
			for(int i=0; i<pre.size(); i++) {
				//Makes sure there is an instance
				if(pre.get(i) != null) {
					//If it is windy and it is sunny out
				if(pre.get(i).getWindy() == true && pre.get(i).getOutlook().equals("sunny")) {
					pre.get(i).setPlay("swimming");
				}
				//If it is not windy
				else if(pre.get(i).getWindy() == false) {
					pre.get(i).setPlay("tennis");
				}
				//If it is windy and rainy, stay inside
				else if(pre.get(i).getWindy() == true && pre.get(i).getOutlook().equals("rainy")) {
					pre.get(i).setPlay("video games");
				}
				//any other conditions and you go for a run
				else {
					pre.get(i).setPlay("running");
				}
			}
			}
		}
		
		//Sets everything up for random Instance
		public void initializeRandom() {
			weather = new String [] {"sunny" , "rainy", "overcast", "tornado"};
			activity = new String [] {"running", "video games", "swimming", "tennis"};
			rng = new Random();
		}
		
		//makes the random Instance
		//Creates number ranges and then assigns values based on random numbers
		public Instance randomInstance() {
		 yep = new Instance();
			big = rng.nextInt(100);
			yep.setHumidity(big);
			big = rng.nextInt(100);
			yep.setTemperature(big);
			small = rng.nextInt(4);
			yep.setPlay(activity[small]);
			small = rng.nextInt(4);
			yep.setOutlook(weather[small]);
			coin = rng.nextInt(2);
			if(coin == 0) {
				yep.setWindy(false);
			}
			else if (coin == 1) {
				yep.setWindy(true);
			}
			
			//returns the randomized instance
			return yep;
		}
		//returns the activity based on scenerio
		public String getActivity(String outlook, int temp, int humid, boolean wind) {
			
					//If it is windy and it is sunny out
				if(wind == true && outlook.equals("sunny")) {
					thing = "swimming";
				}
				//If it is not windy
				else if(wind == false) {
					thing = "tennis";
				}
				//If it is windy and rainy, stay inside
				else if(wind == true && outlook.equals("rainy")) {
					thing = "video games";
				}
				//any other conditions and you go for a run
				else {
					thing = "running";
				}
			
			return thing;
		}
		
		//returns possible activities
		public String [] getActivities(){
			acty = new ArrayList <String>();
			for(Instance instance : pre) {
					if (!acty.contains(instance.getPlay())) {
						acty.add(instance.getPlay());
				}
			
			}
			String [] toReturn = new String [acty.size()];
			int index = 0;
			for(String string : acty) {
				toReturn[index] = string;
				index++;
			}
			//return
			return toReturn;
		}
		
		//Added for project 3
		public String [] getWeather(){
			wety = new ArrayList <String>();
			for(Instance instance : pre) {
					if (!wety.contains(instance.getOutlook())) {
						wety.add(instance.getOutlook());
				}
			
			}
			String [] toReturn = new String [wety.size()];
			int index = 0;
			for(String string : wety) {
				toReturn[index] = string;
				index++;
			}
			//return
			return toReturn;
		}
		
		//toString method for predictor class
		public String toString() {
			String toReturn = "";
			for(Instance instance: pre) {
				toReturn += instance + "\n";
			}
			return toReturn;
		}
		

		//Reads in the arff file stolen from complexRoster
	private void readFile () {
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
				System.out.println(line);
				//Array of strings. Each string is separated by a comma on the line so the token stores each word.
				String[] tokens = line.split(",");
				String outlook = tokens[0];
				String temperature = tokens[1];
				String humidity = tokens[2];
				String windy = tokens[3];
				String play = tokens[4];
					addInstance(new Instance(outlook,Integer.parseInt(temperature),Integer.parseInt(humidity), Boolean.parseBoolean(windy), play));
					
				
				}			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("there was a problem with the file reader, try different read type.");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while ((line = lineReader.readLine())!=null) {
					System.out.println(line);
					//Array of strings. Each string is separated by a comma on the line so the token stores each word.
					String[] tokens = line.split(",");
					String outlook = tokens[0];
					String temperature = tokens[1];
					String humidity = tokens[2];
					String windy = tokens[3];
					String play = tokens[4];
					addInstance(new Instance(outlook,Integer.parseInt(temperature),Integer.parseInt(humidity), Boolean.parseBoolean(windy), play));
						}
			} catch (Exception e2) {
				e.printStackTrace();
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	} // end of readFile method	

		//Writes to file that I pulled data from
	public void writeFile () {
		// overloaded method: this calls doWrite with file used to read data
		// use this for saving data between runs
		doWrite(fileName);
	} // end of writeFile method

	//Writes to an additional folder I added
	public void writeFile(String altFileName) {
		// overloaded method: this calls doWrite with different file name 
		// use this for testing write
		doWrite(altFileName);		
	}// end of writeFile method

	
	void doWrite(String fn) {
		// this method writes all of the data in the persons array to a file
		try
		{

			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);			

			//goes through all the instances in the ArrayList
			for(Instance instance : pre) {
				myOutfile.write (instance.getOutlook() + "," + instance.getTemperature() + "," + instance.getHumidity() + "," + instance.getWindy() + "," + instance.getPlay() + "\n");
			}
			
			//Alternate way to go through the ArrayList
//			for (int i = 0; i < pre.size(); i++) {
//				if(pre.get(i) != null) {
//				myOutfile.write (pre.get(i).getOutlook() + "," + pre.get(i).getTemperature() + "," + pre.get(i).getHumidity() + "," + pre.get(i).getWindy() + "," + pre.get(i).getPlay() + "\n");
//				
//				}
//			}
			myOutfile.flush();
			myOutfile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
	}

	public Iterator<Instance> getIterator() {
		return pre.iterator();
	}	





}

