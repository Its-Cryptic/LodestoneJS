EntityEvents.death(event => {
    let {level, entity} = event
    LodestoneJSUtils.spawnWorldEvent('mymod:myworldevent', level, data => {
        data.write("duration", getDuration())
    })
    console.log('spawned world event')
})

let nextValue = 300;

function getDuration() {
    var v1 = 300;
    var v2 = 600;
 
    var returnValue = nextValue;
    nextValue = nextValue == v1 ? v2 : v1;
    return returnValue;
}