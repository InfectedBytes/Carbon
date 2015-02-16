![logo](http://infectedbytes.com/sites/default/files/small_logo.png)
# Carbon

Carbon is a little pack of extensions for the LibGDX framework. 
Currently there is only one extension: CarbonController

### What is CarbonController?
CarbonController is a wrapper around the LibGDX-Cotnroller extension. 
It is used to hide the real button- and axescodes, so the user can handle each controller with the same codes. 

For example:
The axisCode 0 ist mapped to the Y-axis of the left analogstick on a XBox360 Controller, but it is mapped to the X-axis on an Ouya Controller. 
A CarbonController checks which kind of controller is used and remaps it, so you can always use the same mapping, independent of the real mapping. In the above example, you wold use CarbonAxis.LX and CarbonAxis.LY to get on each device the specified axis. 
