# Vaadin 7 workshop

![Vaadin logo](src/site/vaadin.jpg)

This Vaadin 7 workshop intends to demonstrate some capabilities of Vaadin in general and Vaadin 7 in particular.

## License and terms of use
The project is licensed under the terms of GPL v3, although if you use it - as you're encouraged to do, I would appreciate if you would:

* mention [this Github project](https://github.com/nfrankel/vaadin7-workshop/)
* give [me](https://github.com/nfrankel) credit in your slides
* ping me so that I know who uses it

Feedback and contributions are also much appreciated!

## Demoed features

Features are presented in a step-by-step way. A tag demoes a specific feature (or a tight group thereof):

| Tag | Feature |
|-----|---------|
|[v0.0](https://github.com/nfrankel/vaadin7-workshop/tree/v0.0) | Project generated with Vaadin Maven archetype, but cleaned up to only provide what's necessary
|[v0.1](https://github.com/nfrankel/vaadin7-workshop/tree/v0.1) | Introduces components, layouts, title
|[v0.2](https://github.com/nfrankel/vaadin7-workshop/tree/v0.2) | Proposes an application architecture example, with reusable components
|[v0.3](https://github.com/nfrankel/vaadin7-workshop/tree/v0.3) | Introduces "screen" switch
|[v0.4](https://github.com/nfrankel/vaadin7-workshop/tree/v0.4) | Complete event listener model implementation
|[v0.5](https://github.com/nfrankel/vaadin7-workshop/tree/v0.5) | True push, broadcasted to all clients
|[v0.6](https://github.com/nfrankel/vaadin7-workshop/tree/v0.6) | Set displayed messages in a table instead in a text area

By checking out the desired tag, you can get the state of the project with just the desired state:

    git checkout tag v0.2

# Steps

## Step 0 - Starting off on the right foot

In order to start quickly, we use Maven and the provided artifact. On the command line, type

```
mvn -B archetype:generate  -DarchetypeGroupId=com.vaadin -DarchetypeArtifactId=vaadin-archetype-application -DarchetypeVersion=7.1.1 -DgroupId=ch.frankel.duchessswiss.vaadin -DartifactId=vaadin7-demo -Dversion=1.0-SNAPSHOT -Dpackaging=war
```

This creates a complete project, where some configuration parts are not needed. We may remove them with confidence:

1. In the POM:
    * Remove the `repositories` and `pluginRepositories` sections
	* Remove the commented out dependency
	* Remove the `vaadin-maven-plugin` plugin as it's used only when we need to compile GWT widgetsets
	* Remove the `lifecycle-mapping` plugin since it's only needed when the latter is used (and only in Eclipse)
1. In `MyVaadinUI.java`:
    * Remove `@Theme` as we will not use a custom theme
    * In `@VaadinServletConfiguration`, remove the `widgetset` attribute
1. Remove the `AppWidgetSet.gwt.xml` file (in `src/main/java`)
1. Remove the whole `src/main/webapp` folder

Now is the right time to deploy and play with our newly-created Vaadin application.

## Step 1 - UI proper

In this step, to check results of your updates, wait until Tomcat reload classes and then refresh your browser.

### Components

Adding components in Vaadin is a breeze if you're familiar with Swing or even plain OOP. Just add the wanted component to your layout, *e.g.* to add a text field, just use the following snippet:

```
TextField loginField = new TextField("Login");

layout.addComponent(loginField);
```

Now, try to add some more components on your own. Hint: they all implement `com.vaadin.ui.Component`. Notice nearly most component constructors require a `String` argument; it's their label on the UI.

### Layouts

As for components, layouts are pretty simple for Swing/Flex developers.

Layouts are dedicated components, that can contain other components(`com.vaadin.ui.Layout` extends `com.vaadin.ui.ComponentContainer` which in turn extends `com.vaadin.ui.Component` transitively) so it is very easy to nest layouts inside each other to provide for advanced UIs. However, in real-life Vaadin applications, avoid nesting above 3 levels as legacy browsers/machines will degrade performance poorly.

Change the `VerticalLayout` type to a `HorizontalLayout` type, then to a `FormLayout` and check results at each change. Let's settle on the form layout, as it offers the most pleasant display.

Components can be set a determined height and width while layouts add:

* alignment of a component inside them
* a possible margin (*i.e.* an outer padding)
* a possible spacing (*i.e.* an inner padding)

### Title

Change the displayed page title is as easy as adding a `@Title` annotation on the UI. For dynamic page title, use:

```
Page.getCurrent().setTitle("Something dynamically computed");
```

## Step 2 - Architecturing

OOP is about componentization and reusability. At this point, we only have a single class with everything thrown inside it.

### Basic architecture

At this point, clicking the button just adds a new static label. Instead of this label, try to display the login field value, to check we can get it any time. This requires setting the login field `final` to have a reference to it in the anonymous inner class.

### Reusable listeners

First things first, we should avoid anonymous inner classes for behavior and promote instead top-level abstractions. Use your IDE specifics to achieve this: this means passing component references to the listener constructor (think immutability).

Also, put the newly-created class into a dedicated `ch.frankel.duchessswiss.vaadin.behavior` package and name it `DummyListener`.

### Reusable components

Then, we should do the same for the GUI: separate the UI itself from the login screen. Put this new `LoginScreen`class into a dedicated `ch.frankel.duchessswiss.vaadin.ui` package.
	
### UI name

Since we use Servlet 3.0 feature not to use a dedicated web deployment descriptor, we can easily update our UI name: rename `MyVaadinUI.java` to `MainUI.java` and in its `@VaadinServletConfiguration`, update the `ui` attribute accordingly.

Redeploy and check it produces the same output.

## Step 3 - Switching UIs

The last step enables us to easily switch screens at will.

In order to create a chat application, let's create a new `ChatScreen` with one source `TextArea`, one target `TextArea` and one `Button` to send messages.

Now, change the previous dummy behavior to a login behavior that set the `ChatScreen` as the UI's content:

```
UI.getCurrent().setContent(new ChatScreen());
```

### Session storage

Vaadin shields us from native Java EE API but provides alternative ways to access it. In particular, instead of getting a handle on `javax.servlet.http.HttpSession` to store attributes, Vaadin offers two ways:

* `VaadinSession.getCurrent().setAttribute(Class<T>, Object)`
* `VaadinSession.getCurrent().setAttribute(String, Object)`

Use this to store the login name after login has been processed.

### Notification

In order to send messages to clients, Vaadin provides `com.vaadin.ui.Notification`. Use it to inform login has been successful (or not).

## Step 4 - Push

For a regular chat application, implement behavior so that messages written into the chat screen disappear from the input text area and are displayed into the main text area when the button is clicked using the previous event-listener model.

This implementation is not enough, since it only displays messages to its own sender... not really useful.

### Broadcast push

Broadcast push means that messages are pushed to every connected client. This means several things:

* Create a `BroadcastListener` with an `onMessage`
* Create a `Broadcaster` singleton, that register/unregister `BroadcastListener`s
* `MainUI` should:
    * Implement `BroadcastListener`
	* Add a `BroadcastListener` attribute and a dedicated setter
	* Delegate UI changes to it inside the `access()` method 

Here's the summary class diagram:

![Broadcast Push class diagram](src/site/broadcast_push.png)

## Step 5 - Tabular data

Using a text area for messages display is not optimal. Vaadin provides a dedicated component to display tabular data - `com.vaadin.ui.Table`, and an underlying data model - `com.vaadin.data.util.BeanItemContainer`.

Set a new `BeanItemContainer` as a data source to the table. New messages can be added to the table data model. A message should wrap the sender login, sent time as well as the text.

Table customization can be implemented in several ways:

* Order columns with `table.setVisibleColumns(String...)`
* Columns can be set a width
* Hide column headers with `table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN)`
* Provide a generated column to display time in a relevant way, see the `Table.ColumnGenerator` and the `table.addGeneratedColumn(String)`.

# Going further

If at this point, you're interested to go further into Vaadin, I would suggest some of my personal resources:

* [morevaadin.com](http://morevaadin.com) contains a number of articles on a single dedicated subject
* [Learning Vaadin 7](http://www.packtpub.com/learning-vaadin-7-second-edition/book) is a whole book dedicated to learning Vaadin 7 from the ground up

![Learning Vaadin cover](src/site/learning_vaadin.jpg)



    
