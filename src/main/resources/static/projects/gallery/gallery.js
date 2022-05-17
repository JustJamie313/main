let flashes = 0;
function toggleLight(){
let flickerElements = [document.querySelector('#light'),document.querySelector('#decrease'),document.querySelector('#increase'),document.querySelector('#galleryImage'),document.querySelector('pre'),document.querySelector('#chain')];
if(!flickerElements[0].classList.contains('flicker')){
   for(let a=0;a<flickerElements.length;a++){
      flickerElements[a].classList.add('flicker');
   }
} else {
   while(flashes<10){
      setTimeout(function(){
         toggleLight();
      },Math.floor(Math.random()*1000));
      flashes++;
   };
   for(let a=0;a<flickerElements.length;a++){
      flickerElements[a].classList.remove('flicker');
   }
}
}
function pullChain(){
let chain = document.querySelector('#chain');
    chain.classList.toggle("pulled");
}
window.addEventListener('load',function(){
    document.getElementById("copyDate").innerHTML = new Date().getFullYear();
    displayObject = new DisplayClass("js",document.querySelector('#flamingText').innerHTML);
    displayObject.buildHTML().then(()=>{
        displayObject.randomize();
    });
});

