os.setenv('TT_INSTANCE_NAME', 'instance-001')
os.setenv('TT_LISTEN', '3301')

box.cfg{listen = 3301}
if box.space.KV == nil then
    box.schema.space.create('KV')
    box.space.KV:format({
        { name = 'key', type = 'string' },
        { name = 'value', type = 'varbinary', is_nullable = true }
    })
    box.space.KV:create_index('primary', {
        type = 'tree',
        parts = {'key'}
    })
end
