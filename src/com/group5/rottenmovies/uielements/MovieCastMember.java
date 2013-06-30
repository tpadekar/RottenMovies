/**
 * 
 */
package com.group5.rottenmovies.uielements;

/**
 * @author padekar
 *
 */
public class MovieCastMember {
	
	public MovieCastMember(String actorName, String character) {
		this.actorName = actorName;
		this.character = character;
	}


	private String actorName;
	/**
	 * @return the actorName
	 */
	public String getActorName() {
		return actorName;
	}
	/**
	 * @param actorName the actorName to set
	 */
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	
	
	private String character;
	/**
	 * @return the character
	 */
	public String getCharacter() {
		return character;
	}
	/**
	 * @param character the character to set
	 */
	public void setCharacter(String character) {
		this.character = character;
	}

}
