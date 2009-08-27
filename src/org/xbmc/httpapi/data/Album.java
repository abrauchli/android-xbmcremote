/*
 *      Copyright (C) 2005-2009 Team XBMC
 *      http://xbmc.org
 *
 *  This Program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2, or (at your option)
 *  any later version.
 *
 *  This Program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with XBMC Remote; see the file license.  If not, write to
 *  the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.
 *  http://www.gnu.org/copyleft/gpl.html
 *
 */

package org.xbmc.httpapi.data;

import org.xbmc.android.util.Crc32;

/**
 * The album class keeps the basic album information from the album table
 * as well some of the extended info from the albuminfo table.
 * 
 * @author freezy <f3k@hosts.ch>
 */
public class Album {

	/**
	 * Points to where the album thumbs are stored
	 */
	public final static String THUMB_PREFIX = "special://masterprofile/Thumbnails/Music/";

	/**
	 * Constructor
	 * @param id		Database ID
	 * @param name		Album name
	 * @param artist	Artist
	 */
	public Album(int id, String name, String artist) {
		this.id = id;
		this.name = name;
		this.artist = artist;
	}

	/**
	 * Composes the complete path to the album's thumbnail
	 * @return Path to thumbnail
	 */
	public String getThumbUri() {
		String hex = getCrc();
		return THUMB_PREFIX + hex.charAt(0) + "/" + hex + ".tbn";
	}
	
	/**
	 * Returns the CRC of the album on which the thumb name is based upon.
	 * @return 8-char CRC32
	 */
	public String getCrc() {
		Crc32 crc = new Crc32();
		crc.computeFromLowerCase(name + artist);
		return crc.getHexValue();
	}
	
	/**
	 * Returns true if the album is a compilation, false otherwise. 
	 * @return True if compilation ("Various Artists"), false otherwise.
	 */
	public boolean isVA() {
		return artist.equalsIgnoreCase("Various Artists") 
			|| artist.equalsIgnoreCase("VariousArtists")
			|| artist.equalsIgnoreCase("VA")
			|| artist.equalsIgnoreCase("V A")
			|| artist.equalsIgnoreCase("V.A.") 
			|| artist.equalsIgnoreCase("V. A.");
	}

	/**
	 * Something descriptive
	 */
	public String toString() {
		return "[" + this.id + "] " + this.name + " (" + this.artist + ")";
	}
	
	/**
	 * Database ID
	 */
	public int id;
	/**
	 * Album name
	 */
	public String name;
	/**
	 * Artist name
	 */
	public String artist;
	/**
	 * Year published
	 */
	public int year = -1;
	
	/**
	 * Rating
	 */
	public int rating = -1;
	/**
	 * Genres, separated by " / "
	 */
	public String genres = null;
	/**
	 * Music label
	 */
	public String label = null;	
}