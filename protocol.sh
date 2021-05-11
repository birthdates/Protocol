plugins=("Floodgate" "HolographicDisplays" "ProtocolLib" "ViaBackwards" "ViaRewind" "ViaRewind-Legacy-Support" "ViaVersion")

function loop {

	for plugin in ${plugins[@]}; do
		cd $plugin
		pluginDir=../Patches/$plugin

		patchFile=$pluginDir/protocol.patch

		if [[ $1 -eq 1 ]]
		then
			mkdir $pluginDir
			echo "Creating $patchFile for $plugin"
			git diff master > $patchFile
		else
			echo "Applying $patchFile to $plugin"
			git apply --reject --whitespace=fix $patchFile 
		fi

		cd ".."
	done
	echo "Complete."
}


case $1 in 

	"patch")
		git submodule update --init --recursive
		loop 0
		;;
	"create")
		echo "Removing old patches..."
		rm -r "Patches/"
		mkdir Patches
		
		loop 1
		;;
	*)
		echo "Unknown command."
		exit
		;;
esac