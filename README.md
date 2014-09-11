# Vaadin workshop

![image](src/site/vaadin.png)

This Vaadin project intends to demonstrate some capabilities of the [Vaadin](https://vaadin.com/) web framework.

The material can be presented in different ways:

* As a full-fledged workshop
* As live-coding in front of an audience
* As a tutorial

## License and terms of use

The project is licensed under the terms of GPL v3, although if you use it - as you're encouraged to do, I would appreciate if you would:

* mention [this Github project](https://github.com/nfrankel/vaadin7-workshop/)
* give [me](http://blog.frankel.ch/me) credit in your slides
* ping me so that I know who uses it

Feedback and pull requests are also much appreciated!

## Prerequisites

There are only a few prerequisites to this workshop:

1. **Java**: the minim Java version Vaadin is compatible with is 6. However, version 8 provides lambda expressions.
2. **Maven**: this workshop provides a POM to retrieve dependencies and package the WAR
3. **Your IDE of choice**: get your favorite IDE, be it IntelliJ IDEA, Eclipse or Oracle NetBeans

## Going further

If you're interested to go further into Vaadin, I would suggest some of my personal resources:

* [morevaadin.com](http://morevaadin.com) contains a number of articles on a single dedicated subject
* [Learning Vaadin 7](http://www.packtpub.com/learning-vaadin-7-second-edition/book) is a whole book dedicated to learning Vaadin 7 from the ground up

![Learning Vaadin cover](src/site/learning_vaadin.jpg)

## Demoed features

Features are presented in a step-by-step way. A tag demoes a specific feature (or a tight group thereof):

| Tag | Feature |
|-----|---------|
|[v7.2-1](https://github.com/nfrankel/vaadin7-workshop/tree/v7.2-1) | Starting point
|[v7.2-2](https://github.com/nfrankel/vaadin7-workshop/tree/v7.2-2) | Basic UI stuff
|[v7.2-3](https://github.com/nfrankel/vaadin7-workshop/tree/v7.2-3) | Architecture
|[v7.2-4](https://github.com/nfrankel/vaadin7-workshop/tree/v7.2-4) | More UI stuff
|[v7.2-5](https://github.com/nfrankel/vaadin7-workshop/tree/v7.2-5) | Tabular data
|[v7.2-6](https://github.com/nfrankel/vaadin7-workshop/tree/v7.2-6) | Push

If at any point, you're lagging behind in the workshop, you can easily get to the next step byt checking out the desired tag:

    git checkout v7.2-2

## Steps

### Step 1 - Starting off on the right foot

Vaadin provides a Maven archetype, which can be used with the following command-line:

```
mvn -B archetype:generate -DarchetypeGroupId=com.vaadin -DarchetypeArtifactId=vaadin-archetype-application -DarchetypeVersion=7.2.5 -DgroupId=ch.frankel.vaadin.workshop -DartifactId=vaadin-workshop -Dversion=1.0-SNAPSHOT -Dpackaging=war
```

This will generate a not-so-empty Vaadin application, with more than what is strictly necessary in this workshop. At this step, the project has been cleaned up to remove *widgetset*-specific code and POM configuration.

Now, let's get to work:

1. Create the WAR package with Maven:

```
mvn package
```

1. Look at the generated package
2. Deploy the WAR in a servlet container (*e.g.* Tomcat or Jetty)
3. Play with the application
4. Look at the raw source
5. Look at the generated HTML (with Chrome's Developer Tools or Firebug for Firefox)
6. Finally, look at the code
7. Try to put everything together

### Step 2 - UI Basics

#### Components

In the Vaadin framework, **components** can be added to the GUI to be displayed. Most of them are very similar to those in Swing (*e.g.* `TextField`, `PasswordField`, etc.). They are located under the `com.vaadin.ui` package.

* *Note 1*: some Vaadin components have no visual display, but this is the exception, not the rule.
* *Note 2*: most of Vaadin component constructors require a `String` argument. This is the label associated with the component.
* *Note 3*: `UI` is a container, not a layout - it can contain only a single child.

#### Layouts

**Containers** are specialized components that can have other components (including containers) as children. In turn, **layouts** are specialized containers that lay out their children in a specific way.

Try the following, be sure to check the display after each step:

1. Try adding a few components to the `VerticalLayout`
2. Change the `VerticalLayout` to a `HorizontalLayout`
3. Change the `HorizontalLayout` to a `FormLayout`

*Note*: it is possible to nest layouts inside layouts. However, as they translate as `div`, client computers may run into performance problems rendering too many nested levels.

In case of need, to debug layout issues, use the `debug=true` query parameter.

#### Margin, sizing and alignment

Though complete theming is available through Cascading Style Sheets, it's possible to use just Java to customize appearance. As previously, check the display after each code change:

1. There's no space between the window border and the password label. Use the `setMargin()` method on the layout.
2. Use the `setHeight()` or `setWidht()` on different components. Their parameter is a CSS size (*e.g* `50px` or `100%`). There's a shortcut method for `setHeight("100%")` and `setWidht("100%")` named `setSizeFull()`

*Note*: aligning components inside the layout is of course possible, but require references to the former and the later as well as defined sizes for each of them

#### Title

Check the page title in the browser: it is set to the page's location. Having a relevant title would go a long way toward a better user-experience. To achieve that, Vaadin provide the `Page` object:


```java
Page.getCurrent().setTitle("Vaadin Workshop");
```

*Note*: the static `getCurrent()` method is available on many Vaadin objects. It uses the `ThreadLocal` pattern, so that you don't have to pass references around.

Alternatively, if the title is static, setting the title can be achieved through the `@Title` annotation on the `UI`.

```java
@Title("Vaadin Workshop")
public class MyVaadinUI extends UI {
    ...
}
```

### Step 3 - Architecturing

OOP is about componentization and reusability. At this point, we only have a single class with everything coded inside it, which defeats its purpose.

The following steps consist of architectural improvements, so make sure the displayed result should stay the same throughout of all them. 

#### Dedicated configuration class

Servlet 3.0 and associated annotations is a good way to replace the legacy `web.xml`, despite the overhead to subclass the `VaadinServlet` class. However, this doesn't mean the latter should be an inner class of the `UI`.

Extract the configuration servlet into its own class in the `ch.frankel.vaadin.workshop.web` package.

#### Reusable listeners

Java 8 allows to use lambda expressions in place of single method interfaces. Replace the `Button.ClickListener` with a lambda.

As a general rule, anonymous inner classes (or lambdas) should be avoided and top-level abstractions should be promote instead. Refactor the code to do this. Passing the layout reference as a constructor arguments is necessary (but please do not create accessors, think immutability). Put the newly created class into a dedicated `ch.frankel.vaadin.workshop.behavior` package and name it `DummyListener`. We'll create more useful listeners in this package later.

Also, move the `UI` class into a `ch.frankel.vaadin.workshop.ui` package.

#### Reusable GUI components

The `UI` represents the inner content of the browser window. To make parts of it reusable, they must be made top-level components.

Extract the `FormLayout` into its own dedicated class. This works but binds the component irremediably to the layout.

To make more configurable components, create components that extends `CustomComponent` and add a constructor that accepts a layout. In our case, name the component `LoginScreen` and the layout parameter should be of type `AbstractOrderedLayout`.

A `CustomComponent` is set its content either through its super constructor or through the `setCompositionRoot()` method.

```java
public class LoginScreen extends CustomComponent {

    public LoginScreen(AbstractOrderedLayout layout) {
        super(layout);
    }
}
```

Instantiate the `FormLayout` in the `UI` and pass it to the constructor.

```java
@Override
protected void init(VaadinRequest request) {
    setContent(new LoginScreen(new FormLayout()));
}
```

### Step 4 - More features

Vaadin brings unique and powerful features within your reach. This section is dedicated to a few of them.

#### Switching screens

Vaadin implements the Single Page Interface paradigm. 

An application which always displays the same components is not interesting. This section is dedicated to switch screens.

We will use a chat application mockup as an illustration. As the `LoginScreen` has already been developed previously, we just need a new screen `ChatScreen`. Create this component and for now, just add a simple label as the composition root to have something displayed.

Switching screens is achieved by using the `UI.getCurrent().setContent(...)`. Put it in a dedicated `LoginListener` and set this `LoginListener` as the button's new behavior. Don't implement anything for the login logic yet. Change the button's label accordingly (from "Click me!" to "Login"). Verify that when the button is clicked, the displayed screen changes.

```java
@Override
public void buttonClick(Button.ClickEvent event) {
    UI.getCurrent().setContent(new MainScreen());
}
```

Refresh the browser window (press F5): the display is reset to the first login screen. The reason is that the `init()` method of the `UI` is called. This is not so good when one has to develop screens that are set after a long sequence of screen changes. In order for the server state to be saved across browser refreshes, annotate the `UI` with `@PreserveOnRefresh`.

*Note*: to force `init()` to be called, add `restartApplication` to the query parameters

#### Notifications

Notifications are the Vaadin way to notify the user about events of interest happening. They are accessible through the `Notification` class. Use it to let the user he has been successfully logged in.

```java
Notification.show("You've been successfully logged in");
```

Simple notifications simply disappear when the user moves the mouse, which is not adapted to error. Implement a simple login logic in the listener and display an error notification when the user fails authentication: this is possible through the `Type.ERROR_MESSAGE` parameter in the `show()` method.

```java
Notification.show("Wrong credentials", ERROR_MESSAGE);
```

#### Storing data

Notice Vaadin cleverly hides the JavaEE API, so that there's no straightforward access to many objects, such as `HttpSession`, `HttpServletRequest` and `HttpServletResponse` objects. This doesn't mean that their features are not accessible: in particular, storing data into the user session is provided via the `VaadinSession.getCurrent().setAttribute()` method. Store the login field value into the session and display it as a label on the main screen.

Attributes can be stored by key as usual, but also by class. This makes storing full-fledged objects such as `User` a breeze.

```java
VaadinSession.getCurrent().setAttribute(User.class, user);
```

### Step 5 - Tabular data

For a chat application, the `ChatScreen` needs a few components:

* One `Table` component to display sent messages
* One `TextArea` component to type messages
* One `Button` to effectively send messages

Use what you've learned previously to add those components to the screen as in the following mockup:

![Chat screen mockup](src/site/mockup.png)

#### Simple model

Also, create a `Listener` to move the typed message from the message input to a row in the table. This first requires configuring the table to have a single column of type `String`.

```java
table.addContainerProperty("Message", String.class, null);
```

For the moment, simply use the `Table.addItem(Object[] cells, Object itemId)` method to add a new message.

```java
table.addItem(new Object[] { message }, message.hashCode());
```

Of course, using a table to add a single piece of data is useless. Let's try to add more data:

* Author (*i.e.* the logged-in user)
* Date and time
* And keep the message, of course

#### Container model

Using `Object` arrays to structure the table model is extremely fragile. Fortunately, Vaadin provides an extremely rich structured model. In particular, there're 3 levels of abstraction available:

* `Property` to wrap a simple value (*e.g.* a date)
* `Item` to wrap objects - a collection of properties (*e.g.* a person)
* `Container` to wrap a collection objects

`Container` is extremely well-suited to be displayed in a table component. Each `Item` in a container (*i.e.* row) is identified by a unique identifier dependent on the container's implementation.

![Out-of-the-box container implementations](src/site/container.png)

* `BeanContainer` has to provide a custom identifier generator
* In a `BeanItemContainer`, the `Item` itself is the identifier
* In a `SQLContainer`, the identifier is the *primary key* of the underlying database row

Leaving `SQLContainer` aside for now, choose the right `Container` implementation. Then, create a class to hold author, message and date. Finally, binding the container to the table is extremely simple with the `table.setContainerDataSource()` method.

Note that by using a container data source, **items are to be added on the container instead of the table**. Vaadin out-of-the-box components that are set a data source register it so they will reflect changes to the underlying model.

```java
Message message = new Message(author, text, date);
BeanItemContainer<Message> container = new BeanItemContainer<>(Message.class);
container.addBean(message);
```

#### Customizing tables

Table components can be heavily customized in a variety of ways.

* Headers

    * By default, column headers take the value of the underlying property. However, they can be customized via the `table.setColumnHeaders(String...)`. Use it to change the column header labels.

    * By using a container datasource, the initial order of the columns is hard to guess. Besides, underlying wrapped class could change, so the previous method is quite fragile. An alternate way to customize header labels is throught the `table.setColumnHeader(String, String)` method. The first `String` parameter is the property's name, the second one the column header's label to set. Use this alternate way to change column labels.

    * Column headers can also be hidden. In fact, the default header display is governed by the `columnHeaderMode` property of the table. Check its different possible values and hide headers altogether.
    
* Columns

    * Columns can be hidden or ordered in a different way, both thanks to the `table.setVisibleColumns(String[])`. The array parameter is the ordered list of all container properties to be displayed on the table.
    
    * As any components, columns can be set a width with `table.setColumnWidth(String, int)`. The first parameter is the property name, the second the width in pixel. Use this to resize the author and timestamp columns.
    
    * Column cells rendering can also be customized. By default, the property's value is formatted into a `String` and displayed in a `Label`. Most types are transformed using `toString()`, but some (*e.g.* `java.util.Date`) are handled in a specific way. To change the rendering of a column, implement a new `ColumnGenerator` and add it to the table with `table.addGeneratedColumn(String, ColumnGenerator)`. 
    
        ```java
        @Override
        public Object generateCell(Table source, Object itemId, Object columnId) {
            Item item = source.getItem(itemId);
            Property property = item.getItemProperty(columnId);
            Object value = property.getValue();
            return MyComponent(value);
        ``` 

        The returned type can be either `Component` or `String`. In the latter case, it will be wrapped into a label to be displayed.
        
        Even better, column generators can be used to add entirely new columns. Write such a generator to display a column with a "Delete me" button. Then add a behavior so that when the button is pressed, the associated message is removed from the container. Hint: store the `Item`'s id and the reference to the container with the `Button.setData(Object)` and remove the item with the `BeanItemContainer.removeItem(Object)`.

Extract all table-related code into its own decicated `MessageTable` class.

#### SQL database

At this point, migrating to a SQL database instead of a in-memory container is straightforward: just change the `BeanItemContainer<Message>` type to a `SQLContainer` type.

![SQLContainer dependencies](src/site/sqlcontainer.png)

Of course, some adaptations are in order:

1. The main changes has to be made in the `MessageTable`. Replace the previous `BeanItemContainer` and along, replace `Message` property names by the names of the database columns (`TIME_STAMP`, `AUTHOR` and `TEXT`). Note some of the above objects have to be used, choose wisely!

    ```java
    JDBCConnectionPool connectionPool = ...
    QueryDelegate queryDelegate = ...
    Container container = new SQLContainer(queryDelegate);
    ```
    
    For connection parameters, use the available `Parameters` class.

2. In the click listener, replace usage of the `addBean()` method and the `Message` class with the `addItem()` method and getting the desired property to set its value:

    ```java
    Object rowId = output.addItem();
    RowItem rowItem = (RowItem) output.getItem(rowId);
    rowItem.getItemProperty("DB_COLUMN").setValue(property);
    ```

3. By default, the SQL container doesn't commit changes automatically to the underlying database. Either set it auto-commit or commit each change (add and delete) explicitly.

Try the application wit these changes. Notice that the line added to the table will be empty. In order to sync changes made to the container, call its `refresh()` method.

Icing on the cake, the "add items" feature can be extracted from the listener to the table, so that as to promote encapsulate.

### Step 5 - Push

A messaging application with only one user is not really usable. This is however what happens with the current state of our app.

To fix it, we need messages sent from one client to be broadcasted to other logged in client. This translates into the code with a `Broadcaster` singleton that will take care of:

* Registering clients. At startup, each client will register itself to the broadcaster.
* Un-registering them. Clients **must** be made to unregister so as to prevent memory leaks.
* Broadcasting messages. Messages will be sent to all registered clients.

Vaadin doesn't offer such a class out-of-the-box but it's provided in the project.

Do the following:

1. Make the `UI` implement `BroadcastListener` and override the method to refresh the table.

    *Note*: at present, tables don't refresh the display either throught their container's `refresh()` method or their own `refreshRowCache()` method. Either replace the table with a new instance or the screen with a new screen - the latter is simpler.

2. In the `UI.init()` method, call `Broadcaster.register()` to register the current instance.
3. In the `UI.detach()` method, call the opposite `Broadcaster.unregister()`






