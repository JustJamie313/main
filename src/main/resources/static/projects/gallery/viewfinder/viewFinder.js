
function look(){
    runFade();
    setViewing();
    changeButton();
}
function runFade(){
    let els = document.querySelectorAll('.fade');
    for(let a=0;a<els.length;a++){
        els[a].addEventListener('animationiteration',()=>{
            els[a].style.animationPlayState = 'paused';
        });
        els[a].style.animationPlayState = 'running';
    }
}
function setViewing(){
    let input = document.querySelector("#viewing");
    console.log(input.value);
    if(input.value=="true"){
        input.value = "false";
        return;
    }
    input.value = "true";
    console.log(input.value);
}
function changeButton(){
    let button = document.querySelector('#view');
    if(button.innerHTML==="View"){
        button.innerHTML = "Stop Viewing";
    } else {
        button.innerHTML = "View";
    }
}
function showControls(){
    let controls = document.querySelector('.controls');
    controls.classList.toggle("show");
}