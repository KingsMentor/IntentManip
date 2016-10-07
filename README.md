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
4. Ignorint Component 
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
