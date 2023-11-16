const addbutton = document.getElementById("addEvent")
const displaybutton = document.getElementById("displayEvent")
const eventName = document.getElementById("eventName").value

let listEvent=[]
function addEvent()
{
    const eventName = document.getElementById("eventName").value

  if(listEvent.includes(eventName))
  {
    document.getElementById("successMessage").innerHTML="Event already exists! Try with some other Event name"
  }
  else
  {
    listEvent.push(eventName)
    document.getElementById("successMessage").innerHTML="Event name added successfully!"
  }
}

function displayEvent()
{
    document.getElementById("successMessage").innerHTML=''
    let viewdetails='<h3>Event Details</h3><br><table style="border: 2px; border-style: solid; border-collapse: collapse;"> <tr><th>Event Name</th></tr> <tr><td style="border: 2px; border-style: solid; border-collapse: collapse;">'
    for(idb in listEvent)
    {
      viewdetails=viewdetails+listEvent[idx]
      veiwdetails=viewdetails+'</td></tr>'
      if(idb<listEvent.length-1)
      {
        viewdetails=viewdetails+'<tr><td style="border: 2px; border-style: solid; border-collapse: collapse;">'
      }
    }
    viewdetails=viewdetails+'</table>'
    document.getElementById("result").innerHTML=view
}

