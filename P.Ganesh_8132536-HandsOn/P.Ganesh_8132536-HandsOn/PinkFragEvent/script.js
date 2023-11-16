const addbutton = document.getElementById("addEvent")
const displaybutton = document.getElementById("displayEvent")
const eventName = document.getElementById("eventName").value

let listEvent=[]
function addEvent()
{
    const eventName = document.getElementById("eventName").value

  if(listEvent.includes(eventName))
  {
    document.getElementById("successMessage").innerHTML="Event already exists! Try with some another Event name"
  }
  else
  {
    listEvent.push(eventName)
    document.getElementById("successMessage").innerHTML="Event Name added Successfully!"
  }
}

function displayEvent()
{
    listEvent.sort()
    document.getElementById("successMessage").innerHTML=''
    let viewdetails='<h3>The Event name after sorting is</h3><br><ol><li>'
    for(idb in listEvent)
    {
      viewdetails=viewdetails+listEvent[idb]
      viewdetails=viewdetails+'</li>'
      if(idb<listEvent.length-1)
      {
        viewdetails=viewdetails+'<li>'
      }
    }
    viewdetails=viewdetails+'</ol>'
    document.getElementById("result").innerHTML=viewdetails
}