StartupEvents.registry('lodestone:world_event_type', event => {
    event.create('mymod:myworldevent')
        .setupAdditionalData(data => {
            data.create("tickCount", 0)
            data.create("duration", 600)
        })
        .saveAdditionalData((data, nbt) => {
            nbt.putInt('tickCount', data.read("tickCount"))
            nbt.putInt('duration', data.read("duration"))
        })
        .readAdditionalData((data, nbt) => {
            data.write("tickCount", nbt.getInt('tickCount'))
            data.write("duration", nbt.getInt('duration'))
        })
        .onTick((instance, level) => {
            if (level.isClientSide()) return

            let data = instance.getData()
            let tickCount = data.read("tickCount")
            let duration = data.read("duration")
            
            data.write("tickCount", tickCount + 1)

            if (tickCount + 1 >= duration) {
                instance.end(level)
            }
        })
})