function buyItem(money) {
    var money=money;
    return function (price) {
        return money-price;
    }
}

var xiaom=buyItem(8000);
var xiaoh=buyItem(800)

console.log(xiaom(1000));//7000
console.log(xiaoh(20));//780

var originCash=1000;

function updateEasyCode(cash) {
        var c= (cash||100)+originCash;
        console.log(c);
}

// updateEasyCode(0)---cash|||100---100  发生错误

function easyCadr(cash) {
    if (!cash&&cash!==0){
        cash=100;
    }
    var meony=cash+originCash;
    console.log(meony);

}

function card(){
        var cash=0;
        console.log(arguments);//打印传入的参数
        for (var i=0;i<arguments.length;i++){
            cash+=arguments[i];
        }
        var meony=cash+originCash;

}

var anutie='靓仔'
function callAnutie() {
    'use strict';
    console.log('call;',this.anutie);
}

callAnutie().call(anutie);

var people= {
    name:'xiaoming',
    age:18,
    address:"shenzhen",
    run:function (intersting) {
        return this.name+"爱好："+intersting;
    }
}
people.run("踢足球")

function  aisePeople(name,age,address) {

     this.name=name;
     this.age=age;
     this.address=address;
     this.run=function () {
            console.log(name+"去"+address);
     }
}
var xiaom=new aisePeople('xiaom',18,'american');
xiaom.run();