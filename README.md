# martian-extension
Extension of the martian simulation that includes the ability to read and write martians to and from text files.

martian_manager.jar is the referenced library for every other class, replicating the MartianManager class from
martian-simulation.

The martian reader will only store martians with a valid syntax.
G # # (For GreenMartians: GreenMartian, id, vol)
R # # # (For RedMartians: RedMartian, id, vol, tenacity)
