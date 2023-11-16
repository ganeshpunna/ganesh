const addbutton = document.getElementById("addEvent")
const dispbutton = document.getElementById("displayEvent")
const eventName = document.getElementById("eventName").value

let listEvent = []
function addPrice(){
    const eventId = document.getElementById("eventId").value
    const eventName = document.getElementById("eventName").value
    const price = document.getElementById("price").value
    let b=[]
    b.push(eventId)
    b.push(eventName)
    b.push(price)
    listEvent.push(b)
    display()
}
function display()
{
    let itemnumber=0
    let disptext='<table style="border: 2px; border-style: solid; ">'
    view=view+'<tr><th style="border: 2px; border-style: solid;">ID</th><th style="border: 2px; border-style: solid;">Product Name</th><th style="border: 2px; border-style: solid;">Price</th><th style="border: 2px; border-style: solid;">Quantity</th><th style="border: 2px; border-style: solid;">Remove from cart</th></tr>'
    for(idb of listEvent)
    {
      itemnumber=itemnumber+1
      view=view+'<tr>'
      for(jdc of idb)
      {
        view=view+'<td style="border: 2px; border-style: solid;">'
        view=view+jdc
        view=view+'</td>'
      }
      view=view+'<td style="border: 2px; border-style: solid;"><select id="select'+itemno+'" onchange="calculate()"><option value = "1">1</option><option value = "2">2</option><option value = "3">3</option></select></td>'
      view=view+'<td style="border: 2px; border-style: solid;"><a id="link'+itemno+'" href="#" onclick="removeItem('+idx[0]+');display();calculate();">Remove</a></td>'
      view=view+'</tr>'
    }
    view=view+'</table>'
    document.getElementById("result").innerHTML=view
    calculate()
}

function removeItem(itemnumber)
{
    console.log(itemnumber)
    let g = []
    for(b of listEvent)
        if(b[0]==itemnumber)
            continue
        else
            g.push(b)
listEvent=temp
}

function calculate()
{
    let total = 0
    let rownumber=0
    for(idx of listEvent)
    {
        rownumber=rownumber+1
        let name="#select"+rownumber
        console.log(name)
        let e = document.querySelector(name)
        let Quantity = e.value
        total=total+idx[2]*Quantity

    }
    document.getElementById("successMessage").innerHTML="The total cost is "+total
}