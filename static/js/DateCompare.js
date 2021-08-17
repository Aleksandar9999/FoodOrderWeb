Date.prototype.withoutTime=function(){
    let date=new Date(this);
    date.setHours(0,0,0,0);
    return date;
}