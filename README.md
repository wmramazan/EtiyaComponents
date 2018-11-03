# EtiyaComponents [![platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com) [![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16) [ ![Download](https://api.bintray.com/packages/wmramazan/maven/EtiyaComponents/images/download.svg) ](https://bintray.com/wmramazan/maven/EtiyaComponents/_latestVersion)

An Android library which contains UI components.

![preview](https://github.com/wmramazan/EtiyaComponents/blob/master/device-2018-11-01-161132.png)

## Components
- EtiyaBottomBar
- EtiyaCardView
- EtiyaCardViewGroup
- EtiyaDialog
- EtiyaListItem
- EtiyaMenu
- EtiyaMenuItem
- EtiyaSelectableListItem
- EtiyaSelectableView
- EtiyaSelectableViewGroup
- EtiyaTabItem
- EtiyaTabLayout

## Demo
[![preview](https://play.google.com/intl/en_us/badges/images/badge_new.png)](https://play.google.com/store/apps/details?id=com.etiya.components.example)

## Integration
Just add the dependency to your `build.gradle`:

```groovy
dependencies {
    implementation 'com.etiya.components:etiya-components:0.1.0'
}
```

## Usage

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <com.etiya.components.EtiyaTabLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="@dimen/components_margin">
    
        <com.etiya.components.EtiyaTabItem
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/tab_list_items"
            app:components_tab_item_layout_id="@id/group_list_items"
            app:components_tab_item_selected="true"/>
    
        <com.etiya.components.EtiyaTabItem
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/tab_selectable_view"
            app:components_tab_item_layout_id="@id/group_selectable_view"/>
    
        <com.etiya.components.EtiyaTabItem
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/tab_menu"
            app:components_tab_item_layout_id="@id/group_menu"/>
    
    </com.etiya.components.EtiyaTabLayout>
    
    <com.etiya.components.EtiyaCardViewGroup
        android:id="@+id/group_list_items"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:card_view_group_type="accordion">
        
        <com.etiya.components.EtiyaCardView
            android:id="@+id/card_list_item"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:card_accordion_title="@string/list_item">
        
            <com.etiya.components.EtiyaListItem
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:list_item_title="@string/components"/>
        
        </com.etiya.components.EtiyaCardView>
        
        <com.etiya.components.EtiyaCardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:card_accordion_title="@string/selectable_list_item">
        
            <com.etiya.components.EtiyaSelectableListItem
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:list_item_title="@string/components"
                app:list_item="@string/list_item"
                app:list_item_selected="true" />
        
        </com.etiya.components.EtiyaCardView>
    
    </com.etiya.components.EtiyaCardViewGroup>

</LinearLayout>

```

In order to see other components, you can look at the sample application and read [documentation](https://wmramazan.github.io/EtiyaComponents).

## License
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

## Screenshots
![preview](https://github.com/wmramazan/EtiyaComponents/blob/master/device-2018-11-01-161148.png)
![preview](https://github.com/wmramazan/EtiyaComponents/blob/master/device-2018-11-01-161157.png)
![preview](https://github.com/wmramazan/EtiyaComponents/blob/master/device-2018-11-01-161206.png)

***
[Etiya Mobile Team](https://www.etiya.com/)