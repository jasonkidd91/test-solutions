package hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class ScatterPalindrome {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
//		List<String> input = Arrays.asList("baabbbcdefb");
		List<String> input = getLongString();
				
		System.out.println(scatterPalindrome(input));
		
		
		long duration = (System.currentTimeMillis() - startTime) / 1000;
		System.out.println("elapse time: " + duration);
	}
	
	public static List<Integer> scatterPalindrome(List<String> strToEvaluate) {
		List<Integer> res = new ArrayList<Integer>();
		for(String s : strToEvaluate) {
			TreeMap<String,Integer> m = new TreeMap<String, Integer>();
			int n = s.length();
			
			// table for storing results (2 rows for odd- 
	        // and even-length palindromes 
			int[][] R = new int[2][n+1];
			
			// Find all sub-string palindromes from the  
	        // given input string insert 'guards' to  
	        // iterate easily over s 
			s = "<" + s + ">";
			
			for(int j=0; j<=1; j++) {
				int rp = 0; // length of 'palindrome radius' 
				R[j][0] = 0;
				
				int i = 1;
				while(i <= n) {
//					System.out.println(i + " " + s.charAt(i));
//					System.out.println(s.charAt(i - rp - 1) + " == " + s.charAt(i + j + rp));
					// only handle cases where words is continues same, e.g aa, bbb, ccc
					while(s.charAt(i - rp - 1) == s.charAt(i + j + rp)) {
//						System.out.println(s.charAt(i - rp - 1) + " == " + s.charAt(i + j + rp));
						// Incrementing the length of 
                        // palindromic radius as and  
                        // when we find vaid palindrome 
						rp++;
					}
					// Assigning the found palindromic length 
	                // to odd/even length array 
					R[j][i] = rp;
					
					int k = 1;
//					System.out.println((R[j][i - k]) + "s");
					while ((R[j][i - k] != rp - k) && (k < rp)) { 
//						System.out.println("s");
	                    R[j][i + k] = Math.min(R[j][i - k], rp - k); 
	                    k++; 
	                } 
	                rp = Math.max(rp - k,0); 
	                i += k; 
				}
			}
			
			//remove guard
			s = s.substring(1, s.length()-1); 
		       
	        // Put all obtained palindromes in a hash map to 
	        // find only distinct palindromess 
	        m.put(s.substring(0,1), 1); 
	        for (int i = 1; i < n; i++)  { 
	            for (int j = 0; j <= 1; j++) 
	                for (int rp = R[j][i]; rp > 0; rp--) 
	                   m.put(s.substring(i - rp - 1,  i - rp - 1 + 2 * rp + j), 1); 
	            m.put(s.substring(i, i + 1), 1); 
	        }
	        
	        System.out.println(m);
	        res.add(m.size());
			
		}
		return res;
	}
	
	static List<String> getLongString() {
		return Arrays.asList(
				"mmwxwxeeyuhyklyuphyknlbipbqnwbflinbqhxnzowwiflnzhoxbcjnszowdiizrnobbpcujsdirqnbpudqqczjvdtplqcqzjxzjohcvtkvpofnylqjxpzpqoyhbeackqvqgjofnypsufpkqqaybeaqqgjejsyufnkxluqmadvjsqmzqeynxalutmgdvsqbmzqartogrgkbsrogtrmxkswtedmyxwedycvvheuucvvayhheuuattyphzgtaatpzgafrafkjrcnkjcnzzyyhfahdfadyhnyhsxnsgwxgwwwjbkjbukbubvvmmzzwfnwfhnhsstatuidtoltymahtuuidtolytqimdwhutqidweeqqieietttotuocktuczktzssnnddymymvvuittuvbichsvttwcvbecmkrhoascvkswcveytjmvkwmiugrohacksvoyrixtjxvjfwbjqmrjuiugkhjsoozrixoxjfbrjcqrjuskjwegrsokzorcjswgegraknvqjwgabsrxnvqrwbksrxerkunmyetunmyuytuybb",
				"shshssxesxfesfryrchychabbbabwbdbhbbbwdrhblaraldoiaadroiurujjvwvjwrjehctregbhvcerqiottxgbvlletrqiwguokskdtcxkllsnwlntpwgtugkskdcwkdsvnwlnwptgwudqrcjvwuqrculjuljjtwtvwvttfpfzkhpxzjjpkhvwixbyjbmjwupvcwiiblqybemiwuwcilqeiaqwmaqmzanztyantynywyyndwydcwqxcwfqhxffhrfrttbpzlbvxpznlvxanawpwpjjzzuazzduahdhyyxnxxnzxxxzxjxjyqhfydzqhfnmryvdbvzxnfvyumryxbawmvbtvknxufavwaxyyuxfbzawkmavtknuahpwmamhyxxtymfzkwfavmhpzmmzhyxtzymwfmabybozzzyambyudbyomugidyhgihoottgcgcbbhksyhekpbsayecuchpbzacuchzyyijimkjmfkzvfzxljvsmadxlujsmazoduzhyohyiiatdzpagwltzlaxdzipgyewelzleehaixinyedeeiefdhlindifdlurnzbtahzurnzbtmahzujbmujlfxbglpfxmgyxptmyxhbttqhhbbmqrtnbonqubmsnth",
				"ggttuuaatjktxjukxruvirpviepefufquqygyygcycyybbppmmbbwwyybbrwqbrwkqbkccngzzlhngzezylhecdycsvgbzdzsvysgtjpbuzztyvswztznjrrbhputbdvwzzwnwrryabrhrybfcwdwwjyalrriyifcwvjlifuiqpvfijuqrpqijrqnivhnqihxtveprhhbtqdhxtejprhgabthdfjgiahfiaaoxtoxttt",
				"nnccmmeqeqaenaaiednacdiqdcsdkqsktbttzkbtzkvvbabajtjctcxgxgqqvwvcfwcwwcfhqcdkwqwhqfqdyklkqfsqykolsoffnncmbzcmbzttieiezzezezqmqmmfevmfcevvicvieexxcttcttjdjdaasxsbxkpbwkpwzblzbdlcdcveyhsveyhskwfenplkwikwwsjfpevncplirtlykjwrmwbnxewsjkdppvcertlyvsmajxrzmmgwvrebnxeegcwkdphevsmabegaxzmemgnvreozecgcwhbegahxemnozyjzochxyjunzoufnfvdmffivdmikzkzttzszsbybywrwwrwdwpldwmepwilirmjewmuhpiiyoymsyrjkhmuohkjvoaqkrlpgbemycomysskmdsvneywfktihokjhvoavgwqmetkrawlhcbflsgbemcfmkjsskdvanedwfktiwxhyvgbwknjkmetawahcwbxckkbefrlnsfkfgjaldkwfnxmhqykcbcckjhnnnjkhavchwbxkkbepljrnfjfsglfngmhedshwqkqcccwlwdwjpmkihpnxnnscmahfvahsighhbpmlajefjxrxsagqedvzshjwqwlwifqdwipmgkispxnouwhscmafqasitdfbghexhrmbmaayexbrxafxzjmdznqbvyzjxifwrxoeqsigfsoupluwhgoqftdfbzetujxrbmjttgm",
				"fccfczsrguwchzsrguwhnneexlxltwotxwbopvpztkxbpvpdazpvjtkdazppvjzpvvsxhsxekhh",
				"ttyiwyniwmkinslqmjksisfldqsfjvsejsgfappfdrjyopbistfqveohjaojxsgappvcfyrjyopjbsitzqqfqogdhljasdoonolxblvcfpyjpdysdzqqfcgpdlhkgsxudomfernmowvlblxfdvppepdayjcdtcfhfphkjgxiyxuygmixpfermkvwevxvdvperajcfootbhfphmfjiyyxyvdbglibuxdypkhvefvryfoqcopkbfuuhpjzmyosvzdbbflqvbpukduyhfhyqcdppfkocnfebuujzosxrohzzbfdiqxbrvftmvpkuhdpzbfhocbxkenmeabxrowfchntvzdicyvxjbrkrbqvdftmjwqofzbvbzhbvoxkremvxawfjcnwtlajvecyrvjfkrbnfqacdjkwquamodfvbvtzvtyoalryayvxwjwladvjedlrjfxlnndlfahpcdpkuxvamuldpsjvwttvyxyahlelyqphmtardywdupvxkdlgjyxilkppndjslhepdpimxvpmulhpsjmzwvyexhemlqnpphdmtrxgdugnxpxdqxukgydikpynnoiupjsedimhpmhkdmzdemnpdhxdggmejzsalhknfnshzwxd",
				"ddhqhvqivikkgsgsiixscyxdvsczlyndavzlunuassdubuussdaubattptttpxtrvxvxphppsrvavxpshqppyswasvmqykwwvmytxikwtxycktylxitxcekhyglehgrxxrtxqqxtqscqafskjcafzzkjzzjjiiiirkzwrkzwhhccooffbbjjygyvgpvlphtlcqhtpxvwbcwaqpyyrxvwnbwtxauystyrntrxxfanbusijtfrxddyrfqvanbitpyajfddqyeoarqvtmtpyawqueooxaomitwbbguoxioejibhbemzegiwucejmfkhemnfzaebyvwrucucsmfkaqlnfabyzvvrgdxrdnpucsaqaefzmnillzvrgdxnhppompnibuvvnjuddnpaefzmniilcnhoppsjxowmpaxmlynibuvbvrnfhzjupdxqiccjosyxjokxtlwaxwmlgykybrnfmahmgyzpxhqmcbjmhywxiolasihupbkekytil",
				"uqlluqlllflfyqxyqxfnmkdfnmtzkdrtaztrastsrtrteeaaqqhhmkmkdztdztrrdeybdueybuotlfvusotlffvusfddjjwmwlevmsmmmlevaymsmzmlayywczxzllywhcfasxybjdzlhnkqfasfgogdybbjcrzcfdnkecestnhqffgogrhdobbgcrpzkmcvifecsxpestnsghfuov",
				"ffxnxnnnwwdkxdkjxojrjoatjjqcrrdauktsjbqcrnduklbsfbnetglnbiwfettjcugknntiwtjcuuickbntudicbdlltcutcuupvuxpvxaaiihhgmgmvviidghlatfmdbghjflatylbmtfimoanbjfowtyhahkmplbmrsigwoannovtumwyhahbkmprsgwnvqtuwpmybqwpmmzkczkeceoqoqhhqqlunejrlcunejrskqcskskzwqghfsprxkxzqwpguhtosfbprrxxfqnjpsuctxlopzsbrvkrrefnyvejdscxldpzdrvkreyvnedrdndgnlpgbwrngaxlmpizgbwaxvvlmoizvwvlomwdmdooeessrprpdbjtzdgiebzmyjrtyovdtpstzrgietzmyrtyovvdtupsertvuksekshhlislirsraaucsiuctscikvotkhckdavoklhdxaylxbybssodhukoodhuikoqifqqfqbjbnjjnkjpkpiioozaziaitytybbtylotgmzdylovguehvmzpdvuugacbehptvbwobpqzcuygyugpakxctobptbmwqobbjoqzktcuiygsybcpkguxkwntocvmdxwyvncqbejzoohwktisblcjgeutkwdnncrvzzjoedxwgyvncqetzoghrwljzetdnrjzzqzjvaoegqttgrcszjzqwpvatcusrwpeqyurdejsrsjqyadjkxszrxdjssjoiuatkpexbyrczsxdbpnjhsdejtoigzuuodhxtpeyqbxyrc",
				"ppzzpzpzaaffhhjjoebjwanoesbtsbjwankfsvcskenbftysqkkfvacskebnfyqksabsoorjerqkjmjeqchkgmkjciehkvghpkiusfekvphmuhnpjlusohfnpumluhrnjlolphnjjulawrlpajjacrwajaocxrsaihvjwamofsxsxawviiihvrwkmzfsiosxrwvzgicirkziioadsrzgciadwwmmnnjjwwvvhhkkne"
				);
	}
	
}
