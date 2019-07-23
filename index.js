var mySvg = document.querySelector('#my-svg');
var snap = Snap(mySvg);
var w = mySvg.width.baseVal.value,h = mySvg.height.baseVal.value,cx = w / 2,cy = h / 2;

var radius = 100;
var perimeter = 2 * Math.PI * radius;
var color = '#007ac1';

var circle = snap.circle(cx, cy, radius);
var text = document.querySelector('.percent-text');
text.style.color = color;

function updateGraph(perc) {

  if(document.getElementById("check4").innerHTML == 'Checking Error' && document.getElementById("check5").innerHTML == 'Checking Error'){
  color = '#e6c300';
  text.style.color = '#e6c300';
}

if(perc >= 0.6){
  color = '#e60000';
  text.style.color = color;
}

  // Reset attributes
  circle.attr({
	fill: 'none',
	stroke: color,
	strokeWidth: '0.7cm',
	strokeDasharray: '0 ' + perimeter,
	strokeDashoffset: perimeter * .25 });

  // Animate
  Snap.animate(0, perc, val => {
	circle.attr({
	  strokeDasharray: perimeter * val + ' ' + perimeter * (1 - val) });
	text.innerHTML = Math.round(val * 100) + '%';
  }, 2500, mina.easeinout);
};

function compareLocalTime(result) {
  document.getElementById("percent4").innerHTML = '';
  var ipTime, systemTime, systemHour, systemMinutes, ipTimeSplit, ipHour, ipMinutes;

  ipTime = String(document.getElementById('localTime').innerHTML);

  systemTime = new Date();
  document.getElementById('date').innerHTML = systemTime;

  systemHour = systemTime.getHours().toString();
  systemHour = ("0" + systemHour).slice(-2);

  systemMinutes = systemTime.getMinutes().toString();
  systemMinutes = ("0" + systemMinutes).slice(-2);
  
  ipTimeSplit = ipTime.split(':');

  ipHour = ipTimeSplit[0];
  ipMinutes = ipTimeSplit[1];

  var test4Element = document.getElementById("test4");


  if(!ipTime || !systemTime || !systemHour || !systemMinutes || !ipHour || !ipMinutes){
    test4Element.classList.add("toast--yellow");
    document.getElementById("check4").innerHTML = 'Checking Error';
  }
  else{
    if(systemMinutes == '59' && ipMinutes == '00' && ipHour == Number(systemHour)+1){
      test4Element.classList.add("toast--green");
      document.getElementById("check4").innerHTML = 'Succeed';
    }
    else{
      if(systemHour==ipHour && (systemMinutes >= ipMinutes-2 && systemMinutes <= ipMinutes+5)){
        test4Element.classList.add("toast--green");
        document.getElementById("check4").innerHTML = 'Succeed';
      }
      else{
        test4Element.classList.add("toast--red");
        document.getElementById("check4").innerHTML = 'Failed';
        document.getElementById("percent4").innerHTML = ' - 20%';
        result += 0.2;
      }
    }
  }
  timeZone(result)
};

function timeZone(result){
  document.getElementById("percent5").innerHTML = '';
  var test5Element = document.getElementById("test5");
  var usingElement = document.getElementById("using");
  var systemTimeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;
  var ipTimeZone = document.getElementById("TimeZone").innerHTML;

  if(!systemTimeZone || !ipTimeZone){
    test5Element.classList.add("toast--yellow");
    document.getElementById("check5").innerHTML = 'Checking Error';
  }
  else{
    if(systemTimeZone == ipTimeZone){
      test5Element.classList.add("toast--green");
      document.getElementById("check5").innerHTML = 'Succeed';
    }
    else{
      test5Element.classList.add("toast--red");
      document.getElementById("check5").innerHTML = 'Failed';
      document.getElementById("percent5").innerHTML = ' - 20%';
      result += 0.2;
    }
  }

  if(document.getElementById("check4").innerHTML == 'Checking Error' && document.getElementById("check5").innerHTML == 'Checking Error'){
    usingElement.classList.add("usingYellow");
    document.getElementById("using").innerHTML = 'Checking Error!';
  }
  else{
    if(result >= 0.6){
      usingElement.classList.add("usingRed");
      document.getElementById("using").innerHTML = 'Your Using VPN!';
    }
    else{
      usingElement.classList.add("usingBlue");
      document.getElementById("using").innerHTML = 'Your Not Using VPN.';
    }
  }
  updateGraph(result);
}

jQuery(document).ready(function(){
  jQuery('.toast__close').click(function(e){
    e.preventDefault();
    var parent = $(this).parent('.toast');
    parent.fadeOut("slow", function() { $(this).remove(); } );
  });
});
