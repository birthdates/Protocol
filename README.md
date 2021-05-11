# Protocol
Most of the Spigot protocol plugins in one! (ViaVersions, ProtocolLib, HolographicDisplays, e.t.c)

# Supported Plugins
* ProtocolLib - 4.6.1-SNAPSHOT
* HolographicDisplays - 2.4.6
* FloodGate - 1.0-SNAPSHOT
* ViaRewind-Legacy-Support - 1.3.4
* ViaBackwards - 3.2.1-SNAPSHOT
* ViaRewind - 1.5.3
* ViaVersion - 3.2.2-SNAPSHOT

# Configuration

## Protocol Configuration
```yaml
blacklisted-plugins: []
```
Add a plugin name to blacklist it from loading.

Example:
```yaml
blacklisted-plugins: ["HolographicDisplays"]
```

## Other Configurations
All supported plugins have their own folders with their files/configs in the `Protocol` folder. (i.e `ViaVersion/config.yml`)
