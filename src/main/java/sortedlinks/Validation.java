/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortedlinks;

public class Validation {

    public static boolean ValidateLink(String url) {
        if (url.startsWith(Link.WITHSSL)) {
            url = url.replaceFirst(Link.WITHSSL, "");
        } else if (url.startsWith(Link.WITHOUTSSL)) {
            url = url.replaceFirst(Link.WITHSSL, "");
        }
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == '.') {
                if (url.endsWith(".")) {
                    return false;
                }
                break;
            }
            if (url.charAt(i) == '?' || url.charAt(i) == '/') {
                return false;
            }
        }
        return true;
    }
}
