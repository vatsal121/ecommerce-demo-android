# Ecommerce App by Vatsal Chauhan and Meet Patel
### Repo Link: https://github.com/vatsal121/ecommerce-demo-android
<div align="center">
    <h3>Project Demo</h3>
</div>

<div align="center" style="width: 100%">

![mvcommerce_app](mvcommerce_app.gif)

</div>

### Project Info

  - Android (Java)
  - Room for DB

1) Ecommerce App is a simple application built using android.
  
2) Project Demo
    - Video file is attached to the file for the demo of our project.
    
3) Test Credentials
    - When the user built the application then they have to register to the page 
    - Username and Password is mandatory to login the app

### Features

- Register Page : 
	- 1) This page has a Username, Password and Confirm Password 
	- 2) If user mis-match password and confirmPassword then application will create toast to notify
	- 3) It has a click event to go back to login page
			
			Example : 
			Username : test
			Password : Test
			ConfirmPassword : Test
			
	- 4) After registering the page user will be on login page 

- Login Page : 
	- 1) This Page has a Username and Password to login the application
	- 2) If user enter wrong password then application will create toast to notify
	- 3) Successful Email id and Password will give access to the application
	     

- Home Page: 
	- 1) List of product is been displayed when the user login the page
	- 2) User can add the product to cart 
	- 3) MenuBar has two icon one is to View Cart and second is Logout button.
	- 4) If same product is added to cart again, then it will increase the product quantity in the cart by 1.(User can update the QTY on the cart page also.)

- Cart Page: 
	- 1) List of product user wish to buy is stored in this page 
	- 2) User can add multiple number of same product
	- 3) If the quantity of the product is equal to 0, it will automatically delete the product.
	- 4) User can remove any product from the cart manually by clicking the delete(trash icon) button.
	- 5) Click checkout button will create a alert dialog which will ask the user to proceed to checkout or not.

### Helper Functions

- Helper Functions are located in "./helpers/HelperMethods.java
- Helper functions contains some common functions which are used globally in the site to improve code readability/resuability.
- Example: showToastMessage() : Shows toast message to the user with the given message and toast duration.


##### The functionality achieved are fully implemented by us both.

- Tasks By Vatsal Chauhan and Meet Patel combined
