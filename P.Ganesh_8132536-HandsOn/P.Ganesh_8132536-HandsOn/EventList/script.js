let listEvent = []
function pushElements()
{
    const eventName = document.getElementById("input").value

    if(listEvent.includes(eventName))
    {
        document.getElementById("successMessage").innerHTML="Event name already exists.Try with some otehr name"
    }
    else
    {
        listEvent.push(eventName)
        document.getElementById("successMessage").innerHTML="Added successfully!"
    }
}

function displayEvent()
{
    document.getElementById("successMessage").innerHTML=''
    let view='<h3>The Event is added in array</h3><br><ul><li>'
    for(idb in listEvent)
    {
      viewdetails=viewdetails+listEvent[idx]
      viewdetails=viewdetails+'</td></tr>'
      if(idx<listEvent.length-1)
      {
        view=view+'<li>'
      }
    }
    view=view+'</ul>'
    document.getElementById("result").innerHTML=view
}

function popElements()
{
    let b = listEvent.pop()
    if(b!=undefined)
    {
        document.getElementById("successMessage").innerHTML="Removed successfully!"
    }
    else
    document.getElementById("successMessage").innerHTML="Array is Empty"
}