package com.runPythonScripts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;



import com.baseClass.GlobalVariables;

public class SummarizationNLTK {
	public static String getSummarizedText(String sentence) {

		//sentence = "Advertising BHU proctor booked for ‘beating up journalist’ The FIR was BHU chief proctor and around half-a-dozen security guards for allegedly beating up a journalist who was making video of a search being carried out in a varsity hostel on Tuesday evening. By Express News Service | Lucknow | Published: March 14, 2019 5:02:12 am Related News SC faculty posts down by half, ST by 80 per cent: Banaras Hindu University alert Outside Banaras Hindu University. (Express Photo by Anand Singh/File)\\nAn FIR has been lodged against Banaras Hindu University (BHU), Varanasi, chief proctor and around half-a-dozen security guards for allegedly beating up a journalist who was making video of a search being carried out in a varsity hostel on Tuesday evening. Advertising\\nThe search was conducted in the Birla hostel after a clash between the students of Lohia and Birla hostels earlier in the day, in which a student from Birla hostel was injured. An FIR was registered against Lohia hostel student, Pawan Mishra, and his two associates. no arrests have been made in connection with both the FIRs registered at the Lanka police station of Varanasi.\\n“On Tuesday evening, Prahlad Pandey, correspondent of a local web portal, was at the Birla hostel where chief proctor Royana Singh was conducting a search. Pandey was making a video of the proceedings for his portal when Royana allegedly snatched his phone and deleted all data from it. She also allegedly directed the guards accompanying her to beat up Pandey. Marks caused by beating by canes are visible all over his body,” informed Bharat Bhushan Tiwari, station house officer (SHO) of the Lanka police station.\\nThe case was registered against Royana under sections 147 (rioting) 323 (voluntarily causing hurt), 504 (intentional insult with intent to provoke breach of the peace), 506 (criminal intimidation), 342 (wrongful confinement) and 392 (robbery) of the Indian Penal Code (IPC), the SHO added. Advertising\\nSSP Sureshrao A Kulkarni, said, “Hours before the search, student Pawan Mishra of Lohia hostel along with his two associates beat up another student, Abhijit Mishra, of Birla hostel near the Arts faculty after an argument. Abhijeet was injured.”\\n“After the incident, students of the Birla hostel held a protest and tried to close the university gate. Police reached the spot, pacified the students and took them to the police station to register the FIR. In the meantime, when chief proctor went to conduct a search in the Birla hostel, students threw stones at the guards. The journalist was allegedly beaten up after that,” added SSP. Latest Videos Long duty hours, no week off: The story of real Chowkidars\\nDenying the charge, chief proctor Royana Singh said it was a routine search and the person “claiming to be a journalist” did not take any permission before making the video.\\n“On Tuesday evening, I was at the hostel to check if there were anyone staying there illegally. The allegations against me are baseless. Media persons generally take permission from us. The person being talked about did not take any permission. Also, there was no attack on anyone as far as I know,” said Royana, adding that during the search in hostels, she found bricks and around 200 glass bottles under the stairs.";

        String s = null;

        try {
            
	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
        	
        	 String[] cmd = {
        		      "python",
        		      "PythonDemoTest.py",
        		      sentence,
        		    };
            Process p = Runtime.getRuntime().exec(cmd);
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");
            
            GlobalVariables globalVariables=new GlobalVariables();
            String[] intent_riot=globalVariables.getIntent_riot();
            String summary="";
            int count=0;
            boolean intentWordFound=true;
            while ((s = stdInput.readLine()) != null) {
            	for(int i=0;i<intent_riot.length;i++) {
                    if(s.toLowerCase().contains(intent_riot[i])) {
                    	count++;
                    }
            	}
            	summary+=" "+s;
            }
            int index=0;
            String rootWord="";
            if(count==0) {
            	for(int i=0;i<intent_riot.length;i++) {
            		if(sentence.toLowerCase().contains(intent_riot[i])) {
            			index=sentence.toLowerCase().indexOf(intent_riot[i]);
            			rootWord=intent_riot[i];
            		}
            	}
            

            List<Integer> indexes=new ArrayList<Integer>();
            
            int startIndex=0;
            while(startIndex != -1){
            	startIndex = sentence.toLowerCase().indexOf(".", startIndex);
                if (startIndex != -1) {
                    indexes.add(startIndex);
                    startIndex++;
                }
            }

            int endIndex=0;
            for(int i=0;i<indexes.size();i++) {
            	if(indexes.get(i)<index) {
            		startIndex=indexes.get(i);
            	}
            	if(indexes.get(i)>index) {
            		endIndex=indexes.get(i);
            		break;
            	}
            }
            
             if(startIndex<0)
                 startIndex=0;
            summary+=sentence.substring(startIndex, endIndex);
            }
            //for(int i=0;i<sentence.length();i++) {
            	
            //}
            
            // read any errors from the attempted command
           // System.out.println("Here is the standard error of the command (if any):\n");
            //while ((s = stdError.readLine()) != null) {
            //    System.out.println(s);
           // }
            
            return summary;
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

	public static void main(String args[]) {
		String sentence = "Advertising BHU proctor booked for ‘beating up journalist’ The FIR was BHU chief proctor and around half-a-dozen security guards for allegedly beating up a journalist who was making video of a search being carried out in a varsity hostel on Tuesday evening. By Express News Service | Lucknow | Published: March 14, 2019 5:02:12 am Related News SC faculty posts down by half, ST by 80 per cent: Banaras Hindu University alert Outside Banaras Hindu University. (Express Photo by Anand Singh/File)\\nAn FIR has been lodged against Banaras Hindu University (BHU), Varanasi, chief proctor and around half-a-dozen security guards for allegedly beating up a journalist who was making video of a search being carried out in a varsity hostel on Tuesday evening. Advertising\\nThe search was conducted in the Birla hostel after a clash between the students of Lohia and Birla hostels earlier in the day, in which a student from Birla hostel was injured. An FIR was registered against Lohia hostel student, Pawan Mishra, and his two associates. no arrests have been made in connection with both the FIRs registered at the Lanka police station of Varanasi.\\n“On Tuesday evening, Prahlad Pandey, correspondent of a local web portal, was at the Birla hostel where chief proctor Royana Singh was conducting a search. Pandey was making a video of the proceedings for his portal when Royana allegedly snatched his phone and deleted all data from it. She also allegedly directed the guards accompanying her to beat up Pandey. Marks caused by beating by canes are visible all over his body,” informed Bharat Bhushan Tiwari, station house officer (SHO) of the Lanka police station.\\nThe case was registered against Royana under sections 147 (rioting) 323 (voluntarily causing hurt), 504 (intentional insult with intent to provoke breach of the peace), 506 (criminal intimidation), 342 (wrongful confinement) and 392 (robbery) of the Indian Penal Code (IPC), the SHO added. Advertising\\nSSP Sureshrao A Kulkarni, said, “Hours before the search, student Pawan Mishra of Lohia hostel along with his two associates beat up another student, Abhijit Mishra, of Birla hostel near the Arts faculty after an argument. Abhijeet was injured.”\\n“After the incident, students of the Birla hostel held a protest and tried to close the university gate. Police reached the spot, pacified the students and took them to the police station to register the FIR. In the meantime, when chief proctor went to conduct a search in the Birla hostel, students threw stones at the guards. The journalist was allegedly beaten up after that,” added SSP. Latest Videos Long duty hours, no week off: The story of real Chowkidars\\nDenying the charge, chief proctor Royana Singh said it was a routine search and the person “claiming to be a journalist” did not take any permission before making the video.\\n“On Tuesday evening, I was at the hostel to check if there were anyone staying there illegally. The allegations against me are baseless. Media persons generally take permission from us. The person being talked about did not take any permission. Also, there was no attack on anyone as far as I know,” said Royana, adding that during the search in hostels, she found bricks and around 200 glass bottles under the stairs.";

		System.out.println(getSummarizedText(sentence));
	}
}
