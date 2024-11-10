## Group members && GitHub id

Yitong An 
Haoze Li
Ze Kun Song
Owen Hexiang Wang- Adidazdoge
Xiaoshu Lin - Michael-lin6677

## Software Specification
The game is a 60-day simulation in which the player acts as a group leader navigating 
the challenges of a zombie apocalypse. Players allocate a set number of initial points 
across resources (e.g., people, weaponry, food, luck) before the first day. These choices 
impact the group's survival strategies and outcomes. Each day, the program provides a 
brief description of the group's current location and what has transpired, such as how 
many people survived or died, the quantity of resources found, or any weaponry acquired.


### User Stories and Functional Requirements

**1. Allocation of Remaining Points** - Yitong An
- **Feature**: Points Allocation System
- **As a player**:
    - I want to allocate my remaining points to my Team, Weapons, Food, and Luck,
    - so I can strengthen specific areas and increase my chances of survival.
- **Requirements**:
    - Players can see how many points are available to allocate.
    - Players can distribute points to Team, Weapons, Food, or Luck as they choose.
    - Allocated points are applied immediately and affect the player’s stats.

**2. Status Check** - Haoze Li
- **Feature**: Status Update Interface
- **As a player**:
    - I want to get updates on the current status so I can make informed decisions.
- **Requirements**:
    - Players can view:
        - **Weather**: Information on current weather conditions.
        - **Environment**: Description of surroundings, including potential threats 
        - or opportunities.
        - **Location**: The current position on the map.
        - **Date**: In-game date to help track progression.
        - **Backpack**: Inventory overview, showing items and supplies.
        - **Body State**: Health and energy status of team members.

**3. Decision-Making Actions** - Ze Kun Song
- **Feature**: Action Menu
- **As a player**:
    - I want to make strategic decisions like moving, gathering, defending, 
    - or broadcasting to improve my team’s survival.
- **Requirements**:
    - **Move**: Enables relocation to a new area on the map.
    - **Gather**: Allows collection of resources from the current environment.
    - **Defend**: Option to set up defenses against threats.
    - **Broadcast**: Sends out signals or attempts to locate survivors and information.
    - Actions have context-dependent outcomes, with feedback influenced by 
    - the environment or events.

**4. Interaction with Random Events** - Owen Hexiang Wang
- **Feature**: Random Events Engine
- **As a player**:
    - I want to interact with unexpected events to experience realistic challenges and 
    - surprises.
- **Requirements**:
    - Random events are triggered based on factors like location, weather, 
    - and player actions.
    - Events impact resources, health, or the environment, requiring adaptive strategies.

**5. Game Exit** - Xiaoshu Lin
- **Feature**: Exit Game
- **As a player**:
    - I want the option to end or save my game to resume later.
- **Requirements**:
    - Players can choose to end or save their progress.
    - When ending, the game provides a summary of progress, achievements, and challenges 
    - faced.


