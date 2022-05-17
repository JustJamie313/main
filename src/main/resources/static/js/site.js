function speakThis(event, text){
    event.preventDefault();
    speechSynthesis.speak(new SpeechSynthesisUtterance(text));
}
function goToURL(url){
    window.location.href = url;
}
window.addEventListener('load',function(){
    document.getElementById("copyDate").innerHTML = new Date().getFullYear();
    displayObject = new DisplayClass("js",document.querySelector('#flamingText').innerHTML);
    displayObject.buildHTML().then(()=>{
        displayObject.randomize();
    });
});
class DisplayClass{
    constructor(type,text){
        this.type = type,
        this.text = text
    }
    buildHTML(){
        let promise = new Promise((resolve,reject)=>{
            let textArray = [];
            for(let a=0;a<this.text.length;a++){
                textArray.push(this.text[a]);
                if(this.text[a] === ' '){
                    textArray.push('&nbsp;');
                }
            }
            let flamingText = document.querySelector('#flamingText');
            if(textArray.length > 0){
                flamingText.innerHTML = '';
                textArray.forEach(element => {
                    let letter = document.createElement('h1');
                    letter.classList.add('flamingText');
                    letter.innerHTML = `&#8203;${element}`;
                    flamingText.appendChild(letter);
                });
            }else{
                this.text = 'Domesne';
                this.buildHTML();
            }

            resolve();
        });

        return promise;
    }
    randomize(){
        let outputString = '';
        let id='';
        let tag = '';
        let src;
        switch(this.type){
            case 'css':
                for(let a=0;a<this.text.length;a++){
                    outputString += `h1.flamingText:nth-child(${a+1}){animation-duration: ${Number(3500-Math.random()*2000).toFixed(2)}ms}`;
                }
                id='addedStyle';
                tag='style';
            break;
            case 'js':
                id='addedScript';
                tag='script';
                src='js/animation.js';
            break;
        }
        let newElement = document.createElement(tag);
        if(src){
            newElement.src = src;
        }
        newElement.id = 'addedStyle';
        newElement.innerHTML = outputString;
        document.querySelector('body').appendChild(newElement);
    }
    clearRandomize(){
        try{
            document.querySelector('body').removeChild(document.querySelector('#addedStyle'));
            document.querySelector('body').removeChild(document.querySelector('#addedScript'));
            let textElementArray = document.querySelectorAll('.flamingText');
            textElementArray.forEach(element =>{
                element.style = `unset`;
            });
        } catch(error){

        }
    }
    updateType(type){
        this.clearRandomize();
        this.type = type;
        this.randomize();
    }
    updateText(text){
        this.clearRandomize();
        this.text = text;
        this.buildHTML().then(this.randomize());
        // this.randomize();
    }
}