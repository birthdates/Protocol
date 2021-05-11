plugins=("Floodgate" "HolographicDisplays" "ProtocolLib" "ViaBackwards" "ViaRewind" "ViaRewind-Legacy-Support" "ViaVersion")

function loop {

	if [[ $1 -eq 1 ]]
	then
		echo "Removing old patches..."
		rm "Patches/*"
	fi

	for plugin in ${plugins[@]}; do
		cd $plugin

		pluginDir=../Patches/$plugin

		mkdir $pluginDir

		if [[ $1 -eq 1 ]]
		then
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