package com.ttshrk.view;


/**
 * 
 * @author ttshrk
 *
 */
class Misc {
	private Misc() {
	}

	public static boolean isHankaku(int code) {
		if ((code >= 0x20 && code <= 0x7F ) ||
		     code == 0x0D || code == 0x0A ) {
			return true;
		}
		return false;
	}
	
	public static int weightCount(String source) {
		int count = 0;
		
        for(int i = 0 ; i < source.length() ; i++) {
            char c = source.charAt(i);
            if (isHankaku(c)) {
            	count++;
            }else{
            	count += 2;
            }
        }
        return count;
    }
}
