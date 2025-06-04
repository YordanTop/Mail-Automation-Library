Feature: Testing receivers notification functionality

  Scenario: receiver subscribes for notification from sender
    Given a receiver has email account named "dani.topov1@gmail.com"
    And the receiver select the sender "yordantop1@gmail.com"
    And a receiver and a sender are connected to each other
    Then the receiver "Subscribe" to the sender
    And receiver notification is turn "On"
    Then receiver notification should be turn "On"

  Scenario: receiver unsubscribes for notification from sender
    Given a receiver has email account named "dani.topov1@gmail.com"
    And the receiver select the sender "yordantop1@gmail.com"
    And a receiver and a sender are connected to each other
    Then the receiver "Unsubscribe" to the sender

  Scenario: receiver turns off notification for listening sender
    Given a receiver has email account named "dani.topov1@gmail.com"
    And the receiver select the sender "yordantop1@gmail.com"
    And a receiver and a sender are connected to each other
    And receiver notification is turn "Off"
    Then receiver notification should be turn "Off"

  Scenario: receiver turns on notification for listening sender
    Given a receiver has email account named "dani.topov1@gmail.com"
    And the receiver select the sender "yordantop1@gmail.com"
    And a receiver and a sender are connected to each other
    And receiver notification is turn "On"
    Then receiver notification should be turn "On"