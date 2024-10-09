StartupEvents.registry('lodestone:world_event_type', event => {
    event.create('mymod:myworldevent')
        .setupAdditionalData(data => {
            data.create("tickCount", 0)
            data.create("duration", 600)
            data.create("shouldRender", true)
        })
        .saveAdditionalData((data, nbt) => {
            nbt.putInt('tickCount', data.read("tickCount"))
            nbt.putInt('duration', data.read("duration"))
            nbt.putBoolean('shouldRender', Boolean(data.read("shouldRender")))
        })
        .readAdditionalData((data, nbt) => {
            data.write("tickCount", nbt.getInt('tickCount'))
            data.write("duration", nbt.getInt('duration'))
            data.write("shouldRender", nbt.getBoolean('shouldRender'))
        })
        .tick((instance, level) => {
            //if (level.isClientSide()) return
            console.log(`instance: ${instance}, level: ${level}`)

            let data = instance.getData()
            let tickCount = data.read("tickCount")
            let duration = data.read("duration")
            
            data.write("tickCount", tickCount + 1)

            if (tickCount + 1 >= duration) {
                instance.end(level)
            }
        })
        .isClientSynced(true)
        .render(ctx => {
            let data = ctx.data()
            let poseStack = ctx.poseStack()
            console.log(`PoseStack: ${poseStack}`)
            console.log(`Data: ${data}`)
            poseStack.pushPose()
            let vfxBuilder = VFXBuilders.createWorld()
            let token = token = RenderTypeToken.createCachedToken(LodestoneLib.lodestonePath("textures/painting/lefunny.png"))
            let renderType = LodestoneRenderTypes.TRANSPARENT_TEXTURE_TRIANGLE.applyAndCache(token)
            vfxBuilder.setRenderType(renderType)
            vfxBuilder.renderSphere(poseStack, 5, 30, 30)
            poseStack.popPose()
            console.log("Rendering world event")
        })
        .shouldRender(data => {
            return true
        })
})