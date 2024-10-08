EntityEvents.death(event => {
    let {level, entity} = event
    LodestoneJSUtils.spawnWorldEvent('mymod:myworldevent', level)
    console.log('spawned world event')
})
