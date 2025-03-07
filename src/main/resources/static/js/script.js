console.log("console loaded");


let currentTheme=getTheme();
document.addEventListener("DOMContentLoaded", () =>{
  console.log("Page Loaded: Initializing Theme");
    changeTheme();
  });
  


function changeTheme(){
//  set to web page
changePageTheme(currentTheme,"");

const changeThemeButton =document.querySelector('#theme_change_button');


// set the listener to change the theme button
changeThemeButton.addEventListener("click",(event)=>{
  let oldTheme=currentTheme;
  console.log("change theme button clicked");

  if(currentTheme==="dark") {
    // theme ko light
    currentTheme="light";
    
  }else{
    // theme ko dark karna hai
    currentTheme="dark";
  }
  console.log(currentTheme)
changePageTheme(currentTheme,oldTheme);
});
}
// set theme to local storage
function setTheme(theme) {
    localStorage.setItem("theme",theme)
}

// get theme from local storage

function getTheme(){
    let theme=localStorage.getItem("theme");
  return theme?theme:"light"
}

// change current page theme
function changePageTheme(theme,oldTheme){
    setTheme(currentTheme);
    //   remove the current theme
    if(oldTheme){
    document.querySelector("html").classList.
    remove(oldTheme); 
    }
  //   set the current theme
    document.querySelector("html").classList.add(theme);
    document.querySelector("#theme_change_button").querySelector("span").textContent=theme==="light"?"Dark":"Light"; 
  
}