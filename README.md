# Assessment
Assessment

Please find the screen shot as well.
I tried to fullfill  your all requirements and does not have enough  time.

steps to run the project:

step1: clone the repository into your local computer
step2: open wth project in android studio 
step3:click on run button by connecting physical device to your system

Technical aspects:

1. I have used Retrofit to fetch the data from NY Times server. This netwroking library is more popular nowdays. 
2.I have used recycle view to display list of Articles (which will  give better performance over traditional listview)
3.I have used picasso for displaying images.

I did not cover unit test cases due to less time and faced some problems in fetching data.

Problems I faced:

The json is not correct format.

"id": 100000005955815,
"asset_id": 100000005955815,
"des_facet": ["UNITED STATES INTERNATIONAL RELATIONS"],
		"org_facet": ["BREASTFEEDING", "INFANT FORMULAS", "WORLD HEALTH ORGANIZATION", "HEALTH AND HUMAN SERVICES DEPARTMENT", "STATE DEPARTMENT"],
		"per_facet": "",
		"geo_facet": ["ECUADOR"],
    
    The above fields has some issue while converting JSON to serilized object.
    
    I know unit test case writing and due to time contraint I am unable to finish it.

