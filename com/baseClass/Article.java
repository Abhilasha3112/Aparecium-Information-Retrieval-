package com.baseClass;

public class Article {

	    private String text;
	    private String title;
	    private String published;
	    private String dateExtracted;
	    private String locationExtracted;
	    private String actorAssociated;
	    private String summarizedText;
	    private String geoCoordinates;
         private String electionViolence;
         private String assosActor1;
	private String assosActor2;

	public String getAssosActor1() {
		return assosActor1;
	}

	public void setAssosActor1(String assosActor1) {
		this.assosActor1 = assosActor1;
	}

	public String getAssosActor2() {
		return assosActor2;
	}

	public void setAssosActor2(String assosActor2) {
		this.assosActor2 = assosActor2;
	}

	private String Url;
	public String getElectionViolence() {
		return electionViolence;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String Url) {
		this.Url = Url;
	}

	public void setElectionViolence(String electionViolence) {
		this.electionViolence = electionViolence;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	private String source;
	public String getGeoCoordinates() {
		return geoCoordinates;
	}

	public void setGeoCoordinates(String geoCoordinates) {
		this.geoCoordinates = geoCoordinates;
	}


	    public String getSummarizedText() {
			return summarizedText;
		}

		public void setSummarizedText(String summarizedText) {
			this.summarizedText = summarizedText;
		}

		public String getActorAssociated() {
			return actorAssociated;
		}

		public void setActorAssociated(String actorAssociated) {
			this.actorAssociated = actorAssociated;
		}

		public String getDateExtracted() {
	        return dateExtracted;
	    }

	    public void setDateExtracted(String dateExtracted) {
	        this.dateExtracted = dateExtracted;
	    }

	    public String getLocationExtracted() {
	        return locationExtracted;
	    }

	    public void setLocationExtracted(String locationExtracted) {
	        this.locationExtracted = locationExtracted;
	    }

	    public String getText() {
	        return text;
	    }

	    public void setText(String text) {
	        this.text = text;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getPublished() {
	        return published;
	    }

	    public void setPublished(String published) {
	        this.published = published;
	    }
	}