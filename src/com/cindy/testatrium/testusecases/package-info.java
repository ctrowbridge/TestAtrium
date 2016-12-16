/**
 * Contains User Test Cases used by the TestAtrium project.
 */
package com.cindy.testatrium.testusecases;

/*-
 * http://docs.openatrium.com/open-atrium-webinars/10-tips-tricks-using-open-atrium 
 *
 * Open Atrium Use Cases
 * 
 * So Open Atrium like I said can be used for a lot of things.
 * 
 * It can be used to build something like an extensible intranet, where you are
 * sharing information with staff, affiliates or chapters of your organization,
 * or members of your team in a centralized place where people can find
 * documents, learn about events, have discussions with one another and so
 * forth.
 * 
 * It can be used as a social collaboration platform where you are really
 * engaging citizens or engaging constituents on sharing their ideas, sharing
 * their stories and then sharing back with them information that your
 * organization or your community is doing.
 * 
 * A knowledge management system, we use this, and you're going to see this
 * today for Open Atrium, Open Atrium's documentation system is actually built
 * on Open Atrium. It's used to manage the various documents that are needed to
 * make sure you know how to use Open Atrium, and it's great because you can use
 * it as a way to publish documents, but in the case of Open Atrium, we've
 * actually then used it to engage community members in providing and
 * contributing additional documentation and you'll see that today, it's a great
 * flexibility in the system.
 * 
 * You can also use it for learning management. The ability to track for an
 * educational institution or even an organization or company that is wanting to
 * provide some learning to their staff can engage and use the calendar feature
 * for of course being a calendar, can use the multimedia tools to provide
 * lectures or lessons online, and use the discussions and documentation
 * sections to really provide that kind of online classroom for people.
 */

/*-
 * https://www.drupal.org/node/2752303
 * 
 * Use case: Public space, public section and write permission via group
 * membership
 * 
 * Last updated June 21, 2016. Created on June 20, 2016. Edited by joep.hendrix.
 * Log in to edit this page.
 * 
 * Description
 * ===========
 * Public space with a public Discussion section. Configure write permission
 * managed via Group membership and not via direct space membership.
 * 
 * Assumptions
 * ===========
 * Clean install of OA 7.x-2.64 without any modification of default permission
 * settings.
 * 
 * Permissions
 * ===========
 *   READ access
 *   -----------
 *   Since the space is public and the section is public, every visitor will have
 *   READ access to the space and the content in the public sections.
 *   
 *   WRITE access
 *   ------------
 *   In order to configure write access via group membership, follow the steps
 *   below:
 * 
 *   Step 1: Create group
 *   --------------------
 *   Create a new group and select
 *   "Child's permissions - inherited users in this group's subgroups will
 *    have the permissions of a generic member of that subgroup."
 *    in the "Group user permission inheritance" paragraph.
 * 
 *   Step 2: Add user(s)
 *   -------------------
 *   Add the users you want to have write access to this group.
 * 
 *   Step 3: Add group to space
 *   -------------------------
 *   Go to the space and add the group to the members list.
 */