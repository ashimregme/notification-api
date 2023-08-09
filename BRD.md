# Business Requirements

## User Story

As an admin, I should be able to send push notifications to android and iOS users filtering by a list of tags.

## Acceptance Criteria

| No. | Criteria                                                                                                             |
| --- | -------------------------------------------------------------------------------------------------------------------- |
| AC1 | API should not take more than 10 seconds to respond.                                                                 |
| AC2 | API should be able to handle up to a million users.                                                                  |
| AC3 | API should fetch user tokens from DB filtering by tags sent in request and send notifications to respective devices. |
| AC4 | API should be able to send a notification message containing icon, banner image, title, short and long description.  |
| AC5 | API should not take more than 5 minutes to dispatch notifications to all users.                                      |
