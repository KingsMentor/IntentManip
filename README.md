# IntentManip
Gives more controls over implicit intents creation and the way it is presented to users. [See this](http://belvi.xyz/posts/Handling-Intents) for a more detailed explanation on why you should consider using this library.

[![](https://jitpack.io/v/KingsMentor/IntentManip.svg)](https://jitpack.io/#KingsMentor/IntentManip)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

![Lib Sample](https://github.com/KingsMentor/IntentManip/blob/master/sample.gif)

# Adding to your project.

### step 1
Add it in your root build.gradle at the end of repositories:
```
repositories {
			...
			maven { url "https://jitpack.io" }
		}
```

### step 2
Add the dependency
```
dependencies {
	        compile 'com.github.KingsMentor:IntentManip:v1.0'
	}
```
# Usage :
There are a couple of operations you can do with the library. It includes:

1. Merging
2. Categorizing Intent
3. Appending Intent
4. Ignoring Component 
5. Lookup Component
6. Target Component
7. Component Preference

# Snippets :

#### Merging - merging components of different intents
```java
LaunchIntent.withButtomSheetAsList(this, resolveIntentList, "launch using", new ResolvedIntentListener<ResolveIntent>() {
            @Override
            public void onIntentSelected(ResolveIntent resolveIntent) {

            }
        });
```
##### Note

*launching* *intent* *from* *resolveIntent*

```java 
startActivity(ManipUtils.getLaunchableIntent(resolveIntent));
```

implement `ResolvedIntentListener`  to get the item the user selected from the list

#### Categorising Components

for presenting merged intents in a categorised format

```java
 ResolveCategory pixResolveCategory = CategorisedIntent.categorized(this, MediaIntents.newSelectPictureIntent(), "picture", 1);
        List<ResolveIntent> merge = new MergeIntent().mergeIntents(this, MediaIntents.newSelectPictureIntent(), GeoIntents.newNavigationIntent(""));
        ResolveCategory mergeResolveCategory = CategorisedIntent.categorized(merge, "Geo and Media", 2);
        List<ResolveCategory> resolveCategories = new ArrayList<>();
        resolveCategories.add(pixResolveCategory);
        resolveCategories.add(mergeResolveCategory);
        LaunchIntent.categorised(this, resolveCategories, "Share With", new ResolvedIntentListener<ResolveIntent>() {
            @Override
            public void onIntentSelected(ResolveIntent resolveIntent) {
                startActivity(ManipUtils.getLaunchableIntent(resolveIntent));
            }
        });
	
```

#### Appending Explicit Intents.
for adding explicit intent ( probably activities from your project ) to implicit components.

```java
PreparedIntent preparedIntent = new PreparedIntent(new Intent(this, Sample.class), R.string.sample, R.mipmap.ic_launcher);
        List<ResolveIntent> resolveIntentList = IntentAppend.appendCustomIntent(this, MediaIntents.newSelectPictureIntent(), preparedIntent);
        LaunchIntent.withButtomSheetAsList(this, resolveIntentList, "launch using", new ResolvedIntentListener<ResolveIntent>() {
            @Override
            public void onIntentSelected(ResolveIntent resolveIntent) {
                startActivity(ManipUtils.getLaunchableIntent(resolveIntent));
            }
        });
```
#### Ignore Components
to ignore or skip or remove components that matches a defined naming pattern from the list

```java
List<ResolveIntent> resolveIntentList = mergeIntents(this, MediaIntents.newSelectPictureIntent(), GeoIntents.newNavigationIntent(""));
        IntentIgnore.IgnoreIntentWithName(this, resolveIntentList, new ArrayList<String>(Arrays.asList(new String[]{"Maps"})));
        LaunchIntent.withButtomSheetAsList(this, resolveIntentList, "launch using", new ResolvedIntentListener() {
            @Override
            public void onIntentSelected(Object resolveIntent) {

            }
        });
```

#### LookingUp Components
to find and return components that matches a defined naming pattern 
```java
List<ResolveIntent> resolveIntentList = mergeIntents(this, MediaIntents.newSelectPictureIntent(), GeoIntents.newNavigationIntent(""));
        IntentLookUp.lookUpAppsByAppName(this, resolveIntentList, "Maps");
        LaunchIntent.withButtomSheetAsList(this, resolveIntentList, "launch using", new ResolvedIntentListener() {
            @Override
            public void onIntentSelected(Object resolveIntent) {

            }
        });
```

#### Target a Component
to target a particular component from a list of component. 
returns `null` if not found or return the first item if more than 1 item is found.
```java
List<ResolveIntent> resolveIntentList = mergeIntents(this, MediaIntents.newSelectPictureIntent(), GeoIntents.newNavigationIntent(""));
        ResolveIntent resolveIntent = TargetIntent.targetByAppName(this, resolveIntentList, "Photo");
        if (resolveIntent != null) {
            startActivity(ManipUtils.getLaunchableIntent(resolveIntent));
        }
```

#### Component Preference
This involves using a `PreferenceType` which could be

* ASCENDING - to arange components in an alphabetical order by appname
* DECENDING - to arange components in a decending alphabetical order by app name
* CUSTOM_PACKAGENAME - arrange the list, placing components with supplied package names aboved others.
* CUSTOM_APPNAME - arrange the list, placing components with supplied app names aboved others.
* CUSTOM_REGEX_APPNAME - arrange the list, placing components with app names matching regEx supplied aboved others.
* CUSTOM_REGEX_PACKAGE_NAME - arrange the list, placing components with package names matching regEx supplied aboved others.

Also **note** that CUSTOM Preference type sort the component in same manage the list was provided.

```java
List<ResolveIntent> resolveIntentList = mergeIntents(this, MediaIntents.newSelectPictureIntent(), GeoIntents.newNavigationIntent(""));
        PreferenceIntent.preferredIntent(this, PreferenceType.CUSTOM_APPNAME, new ArrayList<String>(Arrays.asList(new String[]{"Maps","Photo"})), resolveIntentList);
        LaunchIntent.withButtomSheetAsList(this, resolveIntentList, "launch using", new ResolvedIntentListener() {
            @Override
            public void onIntentSelected(Object resolveIntent) {

            }
        });
```

The snippet above will sort the merge component of `MediaIntents` and `GeoIntents` but would place Maps and Photo above the entire list. 
Maps will be above Photos because if was provided that way. To bring Photos above Maps, it has to be this way:

```java
PreferenceIntent.preferredIntent(this, PreferenceType.CUSTOM_APPNAME, new ArrayList<String>(Arrays.asList(new String[]{"Photo","Maps"})), resolveIntentList);
```

## Contributing
Contributions are welcome.



## Credits
[android-intents](https://github.com/marvinlabs/android-intents) and 
[Bottom Sheet](https://github.com/soarcn/BottomSheet) upon which this library is based.

## License

```
Copyright (C) 2016 MarvinLabs 
Copyright (C) 2011, 2015 Kai Liao

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
