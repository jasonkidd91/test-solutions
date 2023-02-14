package experimental;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EnclosedTagChecker {
	
	public static void main(String[] args) throws Exception {
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put('»', '«');
		map.put('}', '{');
		String kvs = map.entrySet().stream().map(e -> e.getKey().toString() + e.getValue().toString()).collect(Collectors.joining());
		String words = longText();
		Pattern pattern = Pattern.compile("[" + kvs + "]");
	    Matcher matcher = pattern.matcher(words);
	    // Check all occurrences
	    Stack<Character> st = new Stack<Character>();
	    boolean nested = true;
	    int lastIndex = -1;
	    while (matcher.find()) {
//	        System.out.print("Start index: " + matcher.start());
//	        System.out.print(" End index: " + matcher.end());
//	        System.out.println(" Found: " + matcher.group());
	    	Character ch = words.charAt(matcher.start());
	    	Character open = map.get(ch);
	    	
	    	// if is closing tag
	    	if(open!=null) {
	    		// if stack is empty
	    		if(st.empty()) {
	    			throw new Exception("unnecessary closing tag at index " + matcher.start() + " - " + words.substring(Integer.max(matcher.start()-10, 0), Integer.min(matcher.start()+10, words.length())));
	    		}
	    		
	    		if(!st.pop().equals(open)) {
	    			throw new Exception("closing tag mismatch at index " + matcher.start());
	    		}
	    		
	    	} else {
	    		// if is opening tag
	    		
	    		// not allowed nested tag
	    		if(!nested) {
	    			if(!st.empty() && st.contains(ch)) {
	    				throw new Exception("nested tag not allowed at index " + matcher.start());
	    			}
	    		}
	    		
	    		st.push(ch);
	    		lastIndex = matcher.start();
	    	}
	    	
	    	
//	    	if(!nested) {
//				if(st.size() > 0 && st.contains(ch)) {
//					System.out.println("Cannot Nested at line " + matcher.start());
//					break;
//				}
//			}
//	    	// is closing tag
//	    	if(map.get(ch)!=null) {
//	    		if(!st.firstElement().equals(map.get(ch))) {
//	    			System.out.println("Unordered closing tag at line " + matcher.start());
//					break;
//	    		}
//	    		st.pop();
//	    	} else {
//	    		st.push(ch);
//	    	}
	    }
	    
	    if(st.size() > 0) {
			System.out.print(st);
			System.out.print(" - there are " + st + " unclosing tagged starting from index " + lastIndex + " - " + words.substring(Integer.max(lastIndex-100, 0), Integer.min(lastIndex+100, words.length())).replaceAll("[\\n]", " "));
			System.out.println();
		} else {
			System.out.println("everything is fine.");
		}
		
	}
	
	private static String longText() {
		return "Our Ref	:	«CompanyNo»/«ProjectNo»/«PhaseNo»/«UnitNo»	Date	:	«SaleDate»\n" + 
				"\n" + 
				"\n" + 
				"Dear Sir/Madam,\n" + 
				"\n" + 
				"PROJECT	:	«ProjectName»\n" + 
				"TYPE	:	«PropertyType»\n" + 
				"	Notice to Prepare Sale and Purchase Agreement and/or Deed Of Mutual Covenants\n" + 
				"\n" + 
				"\n" + 
				"The above subject is referred. We wish to inform you that we have confirmed the sale of the above property. \n" + 
				"\n" + 
				"Please prepare the Sale and Purchase Agreement and/or Deed of Mutual Covenants for execution by the purchaser(s) and us which should contain the following particulars:\n" + 
				"\n" + 
				"Particulars of Purchaser(s)\n" + 
				"Name	:	«Buyer1Name»	NRIC No	:	«Buyer1IdentityNo»\n" + 
				"Name	:	«Buyer2Name»	NRIC No	:	«Buyer2IdentityNo»\n" + 
				"Name	:	«Buyer3Name»	NRIC No	:	«Buyer3IdentityNo»\n" + 
				"Name	:		NRIC No	:	«Buyer4IdentityNo»\n" + 
				"Address	:			«CorrespondenceAddress»	 \n" + 
				"	Contacts of Purchaser(s)\n" + 
				"1st Purchaser\n" + 
				"H/P	:	«Buyer1MobilePhone»	House Tel.:«Buyer1Phone»Office Tel.:«Buyer1OfficePhone»\n" + 
				"\n" + 
				"Email	:	«Buyer1Email»\n" + 
				"\n" + 
				"2nd Purchaser\n" + 
				"H/P	:	«Buyer2MobilePhone»	House Tel.:«Buyer2Phone»Office Tel.:«Buyer2OfficePhone»\n" + 
				"\n" + 
				"Email	:	«Buyer2Email»\n" + 
				"		\n" + 
				"1ST DOWNPAYMENT\n" + 
				"Receipt Date	:	«DocumentDate»\n" + 
				"Amount	:	«CurrencyCode» «DocumentAmmount»\n" + 
				"		continue Page 2 …\n" + 
				"\n" + 
				"Page 2 …\n" + 
				"Our Ref	:	\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"Particulars of Lot\n" + 
				"Phase	:	«PhaseNo»	Parcel No	:	«UnitNo»\n" + 
				"Design Type	:	«UnitLayout»			\n" + 
				"HSD No	:	-	PTD No	:	-\n" + 
				"Master Title/ Geran	:	«TitleNo»\n" + 
				"Building Plan No	:	«BuildingNo»\n" + 
				"Dev. License	:	«DeveloperLicenseNo»\n" + 
				"Advert. Permit	:	«AdvertisementPermitNo»\n" + 
				"Land Area	:	«LandArea»«UOM1»			\n" + 
				"Purchase Price	:	«CurrencyCode»«ContractAmt»	Built Up	:	««BuildUpArea»«UOM1»\n" + 
				"Price Per SQM	:	-	Cost Of The Extra Land	:	RM«AdjRate»«UOM1»\n" + 
				"Accessory Parcel	:	(i) Package: «PackageName»\n" + 
				"					\n" + 
				"\n" + 
				"\n" + 
				"	\n" + 
				"Assistant General Manager		cc: Purchaser(s)\n{"
				;
	}

}