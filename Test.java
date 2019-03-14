import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 25/02/19
 * Description:
 */
public class Test {

    /**
     * @param itemStoreHashCollection Collection of store numbers hashes(that are in the format `item_nbr:store_numbers_hash`) to be decoded
     * @return returns a Map of itemNbr -> Collection of store numbers
     */
    public static String getItemStoreMap1(Collection<String> itemStoreHashCollection) {
//        logger.info("Decoding item store map for set of items");
        Set<String> xx = itemStoreHashCollection
                .stream()
                .map(itemAdded -> itemAdded.split(":"))
                .filter(itemStoreHash -> itemStoreHash.length == 2)
                .map(itemStoreHash -> decodeStoreNumbers(itemStoreHash[0], itemStoreHash[1]))
                .flatMap(Collection::stream).collect(Collectors.toSet());

        return xx.stream().collect(Collectors.joining(","));

    }


    public static void main(String args[]) {
        String ar = "96353299:ar7c95wirtlitsvc92bksl824f4wckhe5p66tt1rzf2coy15k13lellw24lw6oouyu3m08ld7usd1tqqv1c5aljldl4p5cwf7wspu3yxyzcc81utjv692qqgr2jndzb4mf2qi16drfg5p7yi24xocrfk07rr54x6vc18drwa4kk9qr96ztnerals08suav73nrvaamo0qtziwoaicdp9wqcluuugydmibmdc";
        String br = "58693667:1a8bu699381iz1iu7t8uci626of6hzokiuvwwpe4wr25p6tjaf2o8nqsihfd09sojakeqeysdpelqpmb9ymkta1er5fs6v0ti4rysrcdozwkxg9wlh5q5mra6bbbmvq5lyue2c0t6jwkh5f1ts";
        Collection<String> x = new ArrayList<>();
        x.add(ar);
        x.add(br);
        System.out.print(getItemStoreMap1(x));

    }

    private static List<String> decodeStoreNumbers(String itemNumber, String encodedStoreHash) {

//        logger.debug("Decoding item store map for a single item ");

        // 1) convert string to BigInteger with base 36 i.e 12
        BigInteger encodedNbr = new BigInteger(encodedStoreHash, Character.MAX_RADIX);

        // 2) BigInteger to base2 i.e 1100 and reverse i.e 0011
        String binaryArray = reverseString(encodedNbr.toString(2));

        List<Integer> buffer = new ArrayList<>();
        List<String> xx = new ArrayList<>();

        for (int i = 0; i < binaryArray.length(); i++)
            if (binaryArray.charAt(i) == '1') {
                buffer.add(i);

                String x = itemNumber + "#" + i;
                xx.add(x);
            }
        return xx;

//        List<String> temp =  Arrays.asList(buffer.toString().replace("[", "").replace("]", ""));
//            System.out.println("ite: "+itemNumber + "tt"+temp);

//            return temp;



    }

    public static String reverseString(String s) {
        return new StringBuffer(s).reverse().toString();
    }
}
