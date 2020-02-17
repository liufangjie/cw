public class RK{

	private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
	public static void main(String[] args){
		String patternStr = args[0];
		String mainStr = args[1];

		int index = match(patternStr,mainStr);
		System.out.println(index);
	}
	
	public static int match(String patternStr,String mainStr){

		int patternStrLen = patternStr.length();
		int mainStrLen = mainStr.length();
		if(mainStrLen < patternStrLen){
			return -1;
		}

		int patternHash = hash(patternStr);
		System.out.println("patternHash: " + patternHash);

		int mainFirstHash = hash(mainStr.substring(0,patternStrLen));
		System.out.println("mainFirstHash: " + mainFirstHash);

		if(mainFirstHash == patternHash){
			return 0;
		}

		char[] mainValues = mainStr.toCharArray();
		int index = -1;
		int i = 0;
		int mainHash = mainFirstHash;
		for(char mainValue : mainValues){
			System.out.println("shift: " + (i + 1) );
			int endIndex = i + patternStrLen;
			if(endIndex >= mainStrLen){
				break;
			}

			System.out.println("shift char: " + mainValue + " : " + hash(String.valueOf(mainValue)));
			System.out.println("next char: " + mainValues[endIndex] + " : " + hash(String.valueOf(mainValues[endIndex])));
			mainHash = mainHash - hash(String.valueOf(mainValue)) + hash(String.valueOf(mainValues[endIndex]));
			System.out.println("nextHash: " + mainHash);

			if(mainHash == patternHash){
				index = i + 1;
				break;
			}
			i++;
		}
		return index;
	}

	public static int hash(String target){
		if(null == target || target.length() == 0){
			return 0;
		}

		if(target.length() == 0){
			return alpha.indexOf(target) + 1;
		}

		char[] values = target.toCharArray();
		int hash = 0;
		for(char value : values){
			hash+=(alpha.indexOf(value) + 1);
		}
		return hash;

	}

}
