/**
 * java script functions
 */
//show report
function showReport()
{
	if(validateDate())
	{
		return true;
	}
	return false;
}
// add country method
function addCountry()
{
	if(validateName())
	{
		return true;
	}
	return false;
}
//send notification message
function sendMessage()
{
	if(validateMessage())
	{
		return true;
	}
	return false;
}

// add pool method
function addPool()
{
	if(validateName())
	{
		return true;
	}
	return false;
}

// add community method
function addCommunity()
{
	if(validateName() && validateDate() && validateDNS())
	{
		return true;
	}
	return false;
}

// set action method
function cancel()
{
	submitForm(document.forms[0],'cancel');
	return true;
}

// submit form method
function submitForm(form,actionValue)
{
	form.action=actionValue;
	form.submit();
}
/**
 * java script validation methods
 */

// validate name
function validateName()
{
	var name = document.getElementById('name').value;
	if(name.length == 0 ){
		alert('<bean:message key="err.name.invalid" />');
	    return false;
	}
	return true;
}

//validate name
function validateDNS()
{
	var name = document.getElementById('dnsServer').value;
	if(name.length == 0 ){
		alert('<bean:message key="err.dns.invalid" />');
	    return false;
	}
	return true;
}

//validate message body
function validateMessage()
{
	var name = document.getElementById('message').value;
	if(name.length == 0 ){
		alert('<bean:message key="err.empty.message" />');
	    return false;
	}
	return true;
}

// validate date
function validateDate()
{
	var startDate = document.getElementById('effectiveStartDate').value;
	var endDate = document.getElementById('effectiveEndDate').value;
	var dateReg = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/ ;
	if(startDate.length != 10 || endDate.length != 10 ){
		alert('<bean:message key="err.date.invalid" />');
	    return false;
	}
	return dateReg.test(startDate) && dateReg.test(endDate);
}

// validate phone number
function validatePhone()
{
   var phone = document.getElementById('phoneTxt').value;
   var phoneno = /^\d{10}$/;
   if(!phone || phone.lenght == 0)
   {
	   return true;
   }
   if(phone.match(phoneno))
   {  
      return true;  
   }  
   else  
   {  
	  alert('<bean:message key="err.phone.invalid" />');
      return false;  
   }  
}

// validate phone number
function validateMobile()
{
   var phone = document.getElementById('mobileTxt').value;
   var phoneno = /^\d{11}$/;
   if(!phone || phone.lenght == 0)
   {
	   return true;
   }
   if(phone.match(phoneno))
   {  
      return true;  
   }  
   else  
   {  
	  alert('<bean:message key="err.mobile.invalid" />');
      return false;  
   }  
}

// validate email address
function validateEmail() { 
	
	var x = document.getElementById('emailAddress').value;
	if(!x|| x.lenght == 0)
	{
		   return true;
	}
	var atpos = x.indexOf("@");
	var dotpos=x.lastIndexOf(".");
	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
	{
	  alert('<bean:message key="err.email.invalid" />');
	  return false;
	}
	return true;
} 

// validate web site
function validateWebSite() {
    var url = document.getElementById('url').value;
    if(!url || url.lenght == 0)
	{
	   return true;
	}
    var pattern = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    if (pattern.test(url)) {
        return true;
    } 
    alert('<bean:message key="err.website.invalid" />');
    return false;

}

function confirmDelete(){
	var agree=confirm('<bean:message key="app.alert.delete"/>');
	if (agree)
	     return true ;
	else
	     return false ;
}