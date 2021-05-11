plugins=("Floodgate" "HolographicDisplays" "ProtocolLib" "ViaBackwards" "ViaRewind" "ViaRewind-Legacy-Support" "ViaVersion")

function loop {

	if [[ $1 -eq 1 ]]
	then
		echo "Removing old patches..."
		rm -r "Patches/"
		mkdir Patches
	fi

	for plugin in ${plugins[@]}; do
		cd $plugin
		pluginDir=../Patches/$plugin

		if [[ $1 -eq 1 ]]
		then
			mkdir $pluginDir
			git diff > $pluginDir/Patch
		else
			git apply $pluginDir/Patch
		fi

		cd ".."
	done
	echo "Complete."
}


case $1 in 

	"patch")
		loop 0
		;;
	"create")
		loop 1
		;;
	*)
		echo "Unknown command."
		exit
		;;
esac