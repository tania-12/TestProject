public class RolesAndPermissions extends user {
    
	 /**
     * Checks if the admin with specified credentials is registered or not.
     * @param username of the imaginary admin
     * @param password of the imaginary admin
     * @return -1 if admin not found, else index of the admin in the array.
     */
    public int isPrivilegedUserOrNot(String username, String password) {
        int isFound = -1;
        for (int i = 0; i < adminUserNameAndPassword.length; i++) {
            if (username.equals(adminUserNameAndPassword[i][0])) {
                if (password.equals(adminUserNameAndPassword[i][1])) {
                    isFound = i;
                    break;
                }
            }
        }
        return isFound;
    }
	public String userRegistered(String email, String password) {
	    String exists = "0";
	    for (int i = 0; i < registration.userList.size(); i++) {
	        registration user = registration.userList.get(i);
	        if (email.equals(user.getEmail())) {
	            if (password.equals(user.getPassword())) {
	                exists = "1-" + user.getUserID();
	                break;
	            }
	        }
	    }
	    return exists;
	}
}