var elmSubmit = document.getElementById("ID_SUBMIT");
elmSubmit.onclick = function(){
  var elmMail   = document.getElementById("ID_MAIL");
  var elmPassword = document.getElementById("ID_PASSWORD");
  var canSubmit = true;
  if(elmMail.value == "" || elmPassword.value == ""){
    alert("入力漏れの項目があります。");
    canSubmit = false;
  }
  return canSubmit;
}

function CheckPassword(pass){
    var input1 = password.value;
    var input2 = pass.value;
    if(input1 != input2){
      pass.setCustomValidity("入力値が一致しません。");
    }else{
      pass.setCustomValidity('');
    }
  }