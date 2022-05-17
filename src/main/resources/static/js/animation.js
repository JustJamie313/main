runFunction();
function runFunction(){
    let textElementArray = document.querySelectorAll('.flamingText');
    textElementArray.forEach(element =>{
        element.style = `animation-duration: ${Number(2500-(Math.random()*1000)).toFixed(2)}ms`;
    });
}