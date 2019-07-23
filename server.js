var express = require('express');
var request = require('request');
var geoip = require('geoip-lite');
var CountryLanguage = require('country-language');
var bodyParser = require('body-parser')
var app = express();
var ipClient;

app.set('view engine', 'ejs');
app.use(bodyParser.urlencoded({extended:true}));
app.use(bodyParser.json());

var result, accept_language, time, flag, time_zone;
var country, fullCountry, answer, ans;
var test1,test2,test3;
var check1,check2,check3;
var percent1,percent2,percent3;

app.get('/', function(req, res) {
    percent1 = null;
    percent2 = null;
    percent3 = null;
    result = 0;
    //console.log(JSON.stringify(req.headers));
    console.log("\n------------------------------------------------------");
    //ipClient = '217.182.175.75'; //Proxy
    //ipClient = '104.248.140.7'; //VPN
    //ipClient = '109.64.87.92'; //Real IP
    ipClient = req.header('x-forwarded-for');
    accept_language = req.header('accept-language');
    country = geoip.lookup(ipClient)['country'];
    fullCountry = CountryLanguage.getCountry(country).name;
    console.log("Client Connected..");
    console.log(`Client IP: ${ipClient}`);
    console.log("----------------BlackBoxProxyBlock--------------------");
    BlackBoxProxyBlock(res);
})

let port = process.env.PORT;;
if (port == null || port == "") {
    port = 8000;
  }

app.listen(port);
console.log("-------------------");
app.use(express.static(__dirname + '/'));
console.log("Server is running..");

function BlackBoxProxyBlock(res)
{
  request(`http://proxy.mind-media.com/block/proxycheck.php?ip=${ipClient}`,function(error,response,body){
    if(error || body == 'X'){
      console.log('BlackBoxProxyBlock error:', body);
      test1 = 'yellow';
      check1 = 'Checking Error';
    }
    else{
      if(body == 'N'){
          test1 = 'green';
          check1 = 'Succeed';
        }
        else{
          test1 = 'red';
          check1 = 'Failed';
          result += 0.3;
          percent1 = ' - 30%'
        }
        console.log('BlackBoxProxyBlock result is:', body);
      }
    console.log('1. Result is:', result);
    console.log("--------------------HostChecker-----------------------");
    HostChecker(res);
  }
)};



function HostChecker(res)
{
   request(`https://www.ipqualityscore.com/api/json/ip/uglNEXB1BLgftt4M5FyKRFrpdRFk6t0W/${ipClient}`,function(error,response,body){
    var bodyData = JSON.parse(body);
    if(error || bodyData['success'] == false){
      console.log('HostChecker error:', bodyData['success']);
      test2 = 'yellow';
      check2 = 'Checking Error';
    }
    else{
      ans = 0;
      ipNumbers = ipClient.split('.');
      replace = ipNumbers[0]+'-'+ipNumbers[1]+'-'+ipNumbers[2]+'-'+ipNumbers[3];
      ipNumbers.forEach(function (num) {ans += bodyData['host'].includes(num)})
      if(bodyData['host'] == ipClient || (bodyData['region'] == 'N\/A' && bodyData['city'] == 'N\/A') || !bodyData['host'].includes(replace) || ans < 4){
          test2 = 'red';
          check2 = 'Failed';
          result += 0.1;
          percent2 = ' - 10%'
        }
        else{
          test2 = 'green';
          check2 = 'Succeed';
        }
        console.log('HostChecker result is:', bodyData['host'] + ' || Region: ' + bodyData['region'] + ' || City: ' + bodyData['city']);
      }
    console.log('2. Result is:', result);
    console.log("---------------------Time_Zone------------------------");
    Time_Zone(res);
 })
};

function Time_Zone(res){
  request(`https://api.ipgeolocation.io/timezone?apiKey=3f643672d11b4aff9c827233f1e5cb05&ip=${ipClient}`,function(error,response,body){
  if(error || body.includes('not valid')){
    time_zone = undefined;
  }
  else{
    time_zone = JSON.parse(body)['timezone'];
    console.log('Time_Zone result:', time_zone);
  }  
  console.log("------------------Country_Language--------------------");
  Country_Language(res);
  })
};

function Country_Language(res){
  try {
    console.log('iP_Country:',country);
    console.log('Accepted_Language:', accept_language);
  } catch (err) {'Country_Language Function Error!'; test3 = 'yellow'; check3 = 'Checking Error';}
  request(`https://api.ipgeolocation.io/timezone?apiKey=3f643672d11b4aff9c827233f1e5cb05&tz=` + fullCountry ,function(error,response,body){
  if(error || fullCountry == undefined){
    time = undefined;
  }
  else{
    time = JSON.parse(body)['time_24'];
  }  
  
  answer = 0;

  CountryLanguage.getCountryLanguages(country, function (err, languages) {
    if (err) {
      console.log(err);
    }
    else {
      console.log('Languages: ',languages);
      languages.forEach(function (languageCodes) {
        console.log('#.', languageCodes.iso639_1);
        if(languageCodes.iso639_1 == 'en'){
        }
        else{
          answer += accept_language.includes(languageCodes.iso639_1);
        }
        })
      }
    })
  console.log('Country_Language result:', answer);

  if(!country || !accept_language){
    console.log('Country_Language error: Country: ' + country + '  Accepted_Language: ' + accept_language);
    test3 = 'yellow';
    check3 = 'Checking Error';
  }
  else{
    if(country == 'US'){
      test3 = 'yellow';
      check3 = 'Cannot check in US';
    }
    else{
      if(answer == 0){
        test3 = 'red';
        check3 = 'Failed';
        result += 0.2;
        percent3 = ' - 20%'
      }
      else{
        test3 = 'green';
        check3 = 'Succeed';
    }
  }
}
  console.log('3. Result is:', result);
  console.log("--------------------Request End-----------------------");
  flag = 'https://www.countryflags.io/' + country + '/shiny/24.png'
  res.render('index',{result:result,time:time,country:fullCountry,flag:flag,time_zone:time_zone,ipClient:ipClient,test1:test1,test2:test2,test3:test3,check1:check1,check2:check2,check3:check3,percent1,percent2,percent3});
});
};
