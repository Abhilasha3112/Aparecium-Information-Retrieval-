package com.baseClass;

public class GlobalVariables {
	private String[] locationNames = { "India","Madras", "Pradesh", "Adoni", "Amaravati", "Anantapur", "Chandragiri", "Chittoor",
			"Dowlaiswaram", "Eluru", "Guntur", "Kadapa", "Kakinada", "Kurnool", "Machilipatnam", "Nagarjunakoṇḍa",
			"Rajahmundry", "Srikakulam", "Tirupati", "Vijayawada", "Visakhapatnam", "Vizianagaram", "Yemmiganur",
			"Itanagar", "Assam", "Dhuburi", "Dibrugarh", "Dispur", "Guwahati", "Jorhat", "Nagaon", "Sibsagar",
			"Silchar", "Tezpur", "Tinsukia", "Bihar", "Ara", "Baruni", "Begusarai", "Bettiah", "Bhagalpur", "Bihar",
			"Buxar", "Chapra", "Darbhanga", "Dehri", "Dinapur", "Gaya", "Hajipur", "Jamalpur", "Katihar", "Madhubani",
			"Motihari", "Munger", "Muzaffarpur", "Patna", "Purnia", "Pusa", "Saharsa", "Samastipur", "Sasaram",
			"Sitamarhi", "Siwan", "Chandigarh", "Chhattisgarh", "Ambikapur", "Bhilai", "Bilaspur", "Dhamtari", "Durg",
			"Jagdalpur", "Raipur", "Rajnandgaon", "Dadra", "Silvassa", "Daman", "Daman", "Diu", "Delhi", "Delhi",
			"New Delhi", "Goa", "Madgaon", "Panaji", "Gujarat", "Ahmedabad", "Amreli", "Bharuch", "Bhavnagar", "Bhuj",
			"Dwarka", "Gandhinagar", "Godhra", "Jamnagar", "Junagadh", "Kandla", "Khambhat", "Kheda", "Mahesana",
			"Morvi", "Nadiad", "Navsari", "Okha", "Palanpur", "Patan", "Porbandar", "Rajkot", "Surat", "Surendranagar",
			"Valsad", "Veraval", "Haryana", "Ambala", "Bhiwani", "Chandigarh", "Faridabad", "Firozpur Jhirka",
			"Gurgaon", "Hansi", "Hisar", "Jind", "Kaithal", "Karnal", "Kurukshetra", "Panipat", "Pehowa", "Rewari",
			"Rohtak", "Sirsa", "Sonipat", "Himachal Pradesh", "Bilaspur", "Chamba", "Dalhousie", "Dharmshala",
			"Hamirpur", "Kangra", "Kullu", "Mandi", "Nahan", "Shimla", "Una", "Kashmir", "Anantnag", "Baramula", "Doda",
			"Gulmarg", "Jammu", "Kathua", "Leh", "Punch", "Rajauri", "Srinagar", "Udhampur", "Jharkhand", "Bokaro",
			"Chaibasa", "Deoghar", "Dhanbad", "Dumka", "Giridih", "Hazaribag", "Jamshedpur", "Jharia", "Rajmahal",
			"Ranchi", "Saraikela", "Karnataka", "Badami", "Ballari", "Bangalore", "Belgavi", "Bhadravati", "Bidar",
			"Chikkamagaluru", "Chitradurga", "Davangere", "Halebid", "Hassan", "Hubballi-Dharwad", "Kalaburagi",
			"Kolar", "Madikeri", "Mandya", "Mangaluru", "Mysuru", "Raichur", "Shivamogga", "Shravanabelagola",
			"Shrirangapattana", "Tumkuru", "Kerala", "Alappuzha", "Badagara", "Idukki", "Kannur", "Kochi", "Kollam",
			"Kottayam", "Kozhikode", "Mattancheri", "Palakkad", "Thalassery", "Thiruvananthapuram", "Thrissur",
			"Madhya", "Balaghat", "Barwani", "Betul", "Bharhut", "Bhind", "Bhojpur", "Bhopal", "Burhanpur",
			"Chhatarpur", "Chhindwara", "Damoh", "Datia", "Dewas", "Dhar", "Guna", "Gwalior", "Hoshangabad", "Indore",
			"Itarsi", "Jabalpur", "Jhabua", "Khajuraho", "Khandwa", "Khargon", "Maheshwar", "Mandla", "Mandsaur",
			"Mhow", "Morena", "Murwara", "Narsimhapur", "Narsinghgarh", "Narwar", "Neemuch", "Nowgong", "Orchha",
			"Panna", "Raisen", "Rajgarh", "Ratlam", "Rewa", "Sagar", "Sarangpur", "Satna", "Sehore", "Seoni", "Shahdol",
			"Shajapur", "Sheopur", "Shivpuri", "Ujjain", "Vidisha", "Maharashtra", "Ahmadnagar", "Akola", "Amravati",
			"Aurangabad", "Bhandara", "Bhusawal", "Bid", "Buldana", "Chandrapur", "Daulatabad", "Dhule", "Jalgaon",
			"Kalyan", "Karli", "Kolhapur", "Mahabaleshwar", "Malegaon", "Matheran", "Mumbai", "Nagpur", "Nanded",
			"Nashik", "Osmanabad", "Pandharpur", "Parbhani", "Pune", "Ratnagiri", "Sangli", "Satara", "Sevagram",
			"Solapur", "Thane", "Ulhasnagar", "Vasai-Virar", "Wardha", "Yavatmal", "Manipur", "Imphal", "Meghalaya",
			"Cherrapunji", "Shillong", "Mizoram", "Aizawl", "Lunglei", "Nagaland", "Kohima", "Mon", "Phek", "Wokha",
			"Zunheboto", "Odisha", "Balangir", "Baleshwar", "Baripada", "Bhubaneshwar", "Brahmapur", "Cuttack",
			"Dhenkanal", "Keonjhar", "Konark", "Koraput", "Paradip", "Phulabani", "Puri", "Sambalpur", "Udayagiri",
			"Puducherry", "Karaikal", "Mahe", "Puducherry", "Yanam", "Punjab", "Amritsar", "Batala", "Chandigarh",
			"Faridkot", "Firozpur", "Gurdaspur", "Hoshiarpur", "Jalandhar", "Kapurthala", "Ludhiana", "Nabha",
			"Patiala", "Rupnagar", "Sangrur", "Rajasthan", "Abu", "Ajmer", "Alwar", "Amer", "Barmer", "Beawar",
			"Bharatpur", "Bhilwara", "Bikaner", "Bundi", "Chittaurgarh", "Churu", "Dhaulpur", "Dungarpur", "Ganganagar",
			"Hanumangarh", "Jaipur", "Jaisalmer", "Jalor", "Jhalawar", "Jhunjhunu", "Jodhpur", "Kishangarh", "Kota",
			"Merta", "Nagaur", "Nathdwara", "Pali", "Phalodi", "Pushkar", "Sawai Madhopur", "Shahpura", "Sikar",
			"Sirohi", "Tonk", "Udaipur", "Sikkim", "Gangtok", "Gyalsing", "Lachung", "Mangan", "Tamil Nadu", "Arcot",
			"Chengalpattu", "Chennai", "Chidambaram", "Coimbatore", "Cuddalore", "Dharmapuri", "Dindigul", "Erode",
			"Kanchipuram", "Kanniyakumari", "Kodaikanal", "Kumbakonam", "Madurai", "Mamallapuram", "Nagappattinam",
			"Nagercoil", "Palayankottai", "Pudukkottai", "Rajapalaiyam", "Ramanathapuram", "Salem", "Thanjavur",
			"Tiruchchirappalli", "Tirunelveli", "Tiruppur", "Tuticorin", "Udhagamandalam", "Vellore", "Telangana",
			"Hyderabad", "Karimnagar", "Khammam", "Mahbubnagar", "Nizamabad", "Sangareddi", "Warangal", "Tripura",
			"Agartala", "Agra", "Aligarh", "Allahabad", "Amroha", "Ayodhya", "Azamgarh", "Bahraich", "Ballia", "Banda",
			"Bara Banki", "Bareilly", "Basti", "Bijnor", "Bithur", "Budaun", "Bulandshahr", "Deoria", "Etah", "Etawah",
			"Faizabad", "Farrukhabad", "Fatehpur", "Fatehpur", "Ghaziabad", "Ghazipur", "Gonda", "Gorakhpur",
			"Hamirpur", "Hardoi", "Hathras", "Jalaun", "Jaunpur", "Jhansi", "Kannauj", "Kanpur", "Lakhimpur",
			"Lalitpur", "Lucknow", "Mainpuri", "Mathura", "Meerut", "Mirzapur", "Moradabad", "Muzaffarnagar",
			"Partapgarh", "Pilibhit", "Rae Bareli", "Rampur", "Saharanpur", "Sambhal", "Shahjahanpur", "Sitapur",
			"Sultanpur", "Tehri", "Varanasi", "Uttarakhand", "Almora", "Dehra Dun", "Haridwar", "Mussoorie", "Nainital",
			"Pithoragarh", "West Bengal", "Alipore", "Alipur Duar", "Asansol", "Baharampur", "Bally", "Balurghat",
			"Bankura", "Baranagar", "Barasat", "Barrackpore", "Basirhat", "Bhatpara", "Bishnupur", "Budge Budge",
			"Burdwan", "Chandernagore", "Darjiling", "Diamond Harbour", "Dum Dum", "Durgapur", "Halisahar", "Haora",
			"Hugli", "Ingraj Bazar", "Jalpaiguri", "Kalimpong", "Kamarhati", "Kanchrapara", "Kharagpur", "Koch Bihar",
			"Kolkata", "Krishnanagar", "Malda", "Midnapore", "Murshidabad", "Navadwip", "Palashi", "Panihati",
			"Purulia", "Raiganj", "Santipur", "Shantiniketan", "Shrirampur", "Siliguri", "Siuri", "Tamluk",
			"Titagarh" };
	private String intent_riot[] = new String[] { "riot", "protest", "violence", "danga", "unrest" ,"boycott","rioting"};
	private String weekDays[] = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
	private String redundantWords[] = {"how","high","many","are","will","political","party","tamil","nadu","app","subscribe","indian","fbi","people","sun","rise","abuse","serial","central","bureau","investigation","CBI","FBI","probe","rally","shutdown","Image", "use", "only", "only.Image", "Courtesy", "court", "lok", "sabha",
			"University", "News", "Related", "Service", "Express", "morning", "evening", "noon", "varsity", "search",
			"video", "Advertising", "Jan", "January", "Feb", "February", "Mar", "March", "Apr", "April", "May", "Jun",
			"June", "July", "Jul", "Aug", "August", "Sep", "September", "Oct", "October", "Nov", "November", "Dec",
			"December" };


	private String noMeaningWords[]= {"a","the","in","at",".",","};
	private String actors[]= {"student","department","organisation","organization","graduate","children","chief","guard","protestor","protester"};

	public String puncts[]={".",","};
	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public String[] getNoMeaningWords() {
		return noMeaningWords;
	}

	public void setNoMeaningWords(String[] noMeaningWords) {
		this.noMeaningWords = noMeaningWords;
	}

	public String[] getIntent_riot() {
		return intent_riot;
	}

	public void setIntent_riot(String[] intent_riot) {
		this.intent_riot = intent_riot;
	}

	public String[] getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String[] weekDays) {
		this.weekDays = weekDays;
	}

	public String[] getRedundantWords() {
		return redundantWords;
	}

	public void setRedundantWords(String[] redundantWords) {
		this.redundantWords = redundantWords;
	}

	public String[] getLocationNames() {
		return locationNames;
	}

	public void setLocationNames(String[] locationNames) {
		this.locationNames = locationNames;
	}

	public String[] getPuncts() {
		return puncts;
	}

	public void setPuncts(String[] puncts) {
		this.puncts = puncts;
	}
}
